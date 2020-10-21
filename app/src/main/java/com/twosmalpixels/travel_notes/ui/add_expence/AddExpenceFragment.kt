package com.twosmalpixels.travel_notes.ui.add_expence

import BaseFragment
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.createLongFromString
import com.twosmalpixels.travel_notes.core.createStringFromLocation
import com.twosmalpixels.travel_notes.core.extension.getSelectedCurrencyData
import com.twosmalpixels.travel_notes.core.extension.setUri
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import com.twosmalpixels.travel_notes.ui.calendar_dialog.createStringFromDate
import com.twosmalpixels.travel_notes.ui.calendar_dialog.getCurentDate
import com.twosmalpixels.travel_notes.ui.expense_all.ExpenceCategory
import com.twosmalpixels.travel_notes.ui.new_travel.NewTravelsViewModel
import kotlinx.android.synthetic.main.add_expence_fragment.*

class AddExpenceFragment : BaseFragment(), LocationListener, TextWatcher,
    AdapterView.OnItemSelectedListener {
    private lateinit var newTravelsViewModel: NewTravelsViewModel
    private lateinit var addExpenceViewModel: AddExpenceViewModel
    private val PICK_IMAGE = 101
    private val TIME_INTERVAL_LOCATION = 1000 * 60 * 60L
    private val RADIUS_LOCATION = 30F
    private var selectedExpenceCategory: ExpenceCategory? = null

    companion object {
        const val PERMISION_LOCATION = 11
    }

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(getString(R.string.add_expense))
    }

    override fun getLayout(): Int {
        return R.layout.add_expence_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newTravelsViewModel =
            ViewModelProviders.of(requireActivity()).get(NewTravelsViewModel::class.java)
        addExpenceViewModel =
            ViewModelProviders.of(requireActivity()).get(AddExpenceViewModel::class.java)
        newTravelsViewModel.isRangeMode = false
        expence_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        expence_list.adapter = AllExpenceAdapter(ExpenceCategory.values(), {
            // выделенная категория
            selectedExpenceCategory = it
            btnValid()
        })
        initCurrencySoinner()
        initDateCard()
        initFotoCard()
        initLocationCard()
        initLocationLogic()
        edit_text_add_expence.addTextChangedListener(this)
        button_exprnce_save.setOnClickListener {
            //сохраняем расход
            val mainActivity = (requireActivity() as MainActivity)
            val expenceData = ExpenceData(
                createLongFromString(edit_text_add_expence.text.toString()),
                addExpenceViewModel.currentCurrency?.currencyIso ?: "",
                selectedExpenceCategory?.number?.toLong() ?: 0L,
                comment_edit_text_add_expence.text.toString(),
                date_edit_text_add_expence.text.toString(),
                newTravelsViewModel.chooseDate.value?.time ?: 0L,
                getImageName(),
                addExpenceViewModel.lat,
                addExpenceViewModel.lon
            )
            addExpenceViewModel.saveExpenceData(
                mainActivity.db,
                expenceData,
                expenseAllViewModel.travelsItem.docName
            )
        }

        addExpenceViewModel.changeStatus.observe(this, Observer {
            progressViewModel.showProgress.value = false
            if ( it != null && it) {
                //данные успешно записанны, переходим
                findNavController().navigate(R.id.action_addExpenceFragment_to_expenceAllFragment)
            } else if (it != null) {
                //неуспех
                Snackbar.make(requireView(), R.string.error_write, Snackbar.LENGTH_LONG).show()
            }
        })


    }

    private fun getImageName(): String {
        return if (text_foto_add_expence.visibility == View.VISIBLE) {
            ExpenceData.NON_IMAGE
        } else {
            val name = newTravelsViewModel.getRandomFileName()
            newTravelsViewModel.saveBitmap(
                image_add_expence,
                name,
                (requireActivity() as MainActivity).storage
            )
            name
        }
    }

    @SuppressLint("MissingPermission")
    private fun initLocationLogic() {
        val locationManager =
            requireActivity().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager;
        addExpenceViewModel.isHaveLocationPermision.observe(this, Observer {
            if (it) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    TIME_INTERVAL_LOCATION, RADIUS_LOCATION, this
                )
                setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER))
            } else {
                //нет разрешения

            }
        })
        if (ActivityCompat.checkSelfPermission(
                requireActivity() as MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity() as MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity() as MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISION_LOCATION
            );
            return;
        } else {
            addExpenceViewModel.isHaveLocationPermision.value = true
        }

    }

    private fun initCurrencySoinner() {
        spiner_add_expence.adapter = AddExpenceCurrencySpinerAdapter(
            requireContext(),
            R.layout.currency_spiner_item,
            expenseAllViewModel.getCurrencyList()
        )
        spiner_add_expence.setPromptId(R.string.choose_skin_b)
        spiner_add_expence.setSelection(0)
        spiner_add_expence.onItemSelectedListener = this

    }

    private fun initDateCard() {
        date_edit_text_add_expence.setText(createStringFromDate(getCurentDate()))
        date_edit_text_add_expence.setOnFocusChangeListener { view, b ->
            if (b) {
                findNavController().navigate(R.id.action_addExpenceFragment_to_calenarikDialog)
            }
        }
        newTravelsViewModel.chooseDate.observe(this, Observer {
            if (it != null) {
                date_edit_text_add_expence.setText(createStringFromDate(it.time))
            }
        })
    }

    private fun initFotoCard() {
        constraint_foto_add_expence.setOnClickListener { startOpenGalleryIntent() }
    }

    private fun startOpenGalleryIntent() {
        val intentGalerey = Intent()
        intentGalerey.type = "image/*"
        intentGalerey.action = Intent.ACTION_GET_CONTENT

        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val chooser = Intent.createChooser(intentGalerey, "Some text here")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentCamera))
        startActivityForResult(chooser, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            text_foto_add_expence.setVisibility(uri == null)
            image_pluse_foto_add_expence.setVisibility(uri == null)
            image_add_expence.setVisibility(uri != null)
            if (uri != null) {
                image_add_expence.setUri(uri)
            }
        }
    }

    private fun initLocationCard() {
        constraint_location_add_expence.setOnClickListener {
        }
    }

    private fun setLocation(p0: Location?) {
        if (p0 != null) {
            image_location_add_expence.visibility = View.VISIBLE
            pluse_image__location_add_expence.visibility = View.INVISIBLE
            text_location_add_expence.setText(createStringFromLocation(p0))
            addExpenceViewModel.lat = p0.latitude
            addExpenceViewModel.lon = p0.longitude
        }
    }

    override fun onLocationChanged(p0: Location?) {

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    override fun afterTextChanged(p0: Editable?) {
        btnValid()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        addExpenceViewModel.currentCurrency = spiner_add_expence.getSelectedCurrencyData()
    }

    private fun btnValid(){
        val isAmountEmpty = edit_text_add_expence.text?.isEmpty() ?: true
        button_exprnce_save.isEnabled = !isAmountEmpty && selectedExpenceCategory != null
    }
}