package com.twosmalpixels.travel_notes.ui.new_travel

import BaseFragment
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.repositoriy.getInnerName
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.MainActivity
import com.twosmalpixels.travel_notes.ui.calendar_dialog.createStringFromRangeDates
import com.twosmalpixels.travel_notes.ui.choose_travel_skin.ChooseSkinViewModel
import com.twosmalpixels.travel_notes.ui.new_person.NewPersonViewModel
import kotlinx.android.synthetic.main.new_travel_fragment.*

class NewTravelFragment : BaseFragment(), TextWatcher {
    private var isDefaultSkin = true
    private lateinit var newTravelsViewModel: NewTravelsViewModel
    private var blockDublNavigate = false
    private var saveTravelItem: TravelsItem? = null

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(getString(R.string.new_travel))
    }

    override fun getLayout(): Int {
        return R.layout.new_travel_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        blockDublNavigate = true
        super.onViewCreated(view, savedInstanceState)
        val newPersonViewModel =
            ViewModelProviders.of(requireActivity()).get(NewPersonViewModel::class.java)
        val chooseSkinViewModel =
            ViewModelProviders.of(requireActivity()).get(ChooseSkinViewModel::class.java)
        newPersonViewModel.getPerson().observe(this, Observer { who_travel_edit_text.setText(it) })
        chooseSkinViewModel.shoosenSkin.observe(
            this,
            Observer {
                choose_image_travel_main_img.setImageResource(it)
                isDefaultSkin = true
            })
        chooseSkinViewModel.shoosenSkinBitmap.observe(this, Observer {
            choose_image_travel_main_img.setImageBitmap(it)
            isDefaultSkin = false
        })
        where_travel_edit_text.addTextChangedListener(this)
        when_travel_edit_text.setOnFocusChangeListener { view, b ->
            if (b) {
                findNavController().navigate(R.id.action_newTravelFragment_to_calenarikDialog)
            }
        }
        who_travel_edit_text.setOnFocusChangeListener { view, b ->
            if (b) {
                findNavController().navigate(R.id.action_newTravelFragment_to_newPersonFragment)
            }
        }
        newTravelsViewModel =
            ViewModelProviders.of(requireActivity()).get(NewTravelsViewModel::class.java)
        newTravelsViewModel.isRangeMode = true
        button_new_travel_save.setOnClickListener {

            var imageName = newTravelsViewModel.getRandomFileName()

            if (isDefaultSkin) {
                imageName = getInnerName(chooseSkinViewModel.shoosenSkin?.value ?: 0)
            } else {
                newTravelsViewModel.saveBitmap(
                    choose_image_travel_main_img,
                    imageName,
                    (requireActivity() as MainActivity).storage
                )
            }

            saveTravelItem = TravelsItem(
                where_travel_edit_text.text.toString(),
                when_travel_edit_text.text.toString(),
                newTravelsViewModel.chooseDates.value?.get(0)?.time ?: 0L,
                newTravelsViewModel.chooseDates.value?.get(1)?.time ?: 0L,
                who_travel_edit_text.text.toString(),
                imageName,
                newTravelsViewModel.mainCurrencyCode,
                newTravelsViewModel.additionalCurrencyCode,
                newTravelsViewModel.rates
            )
            val mainActivity = (requireActivity() as MainActivity)
            newTravelsViewModel.saveNewTravelData(
                mainActivity.db,
                saveTravelItem!!
            )
            progressViewModel.showProgress.value = true

        }
        newTravelsViewModel.changeStatus.observe(this, Observer {
            progressViewModel.showProgress.value = false
            if (it != null && it && blockDublNavigate) {
                //данные успешно записанны, переходим
                newTravelsViewModel.saveLocalTravelItem(saveTravelItem)
                blockDublNavigate = false
                findNavController().navigate(R.id.action_newTravelFragment_to_youTravelsFragment)
            } else if (it != null && blockDublNavigate) {
                //неуспех
                Snackbar.make(requireView(), R.string.error_write, Snackbar.LENGTH_LONG).show()
            }
        })
        choose_image_travel_constraint.setOnClickListener {
            findNavController().navigate(R.id.action_newTravelFragment_to_chooseTravelSkinFragment)
        }
        newTravelsViewModel.chooseDates.observe(this, Observer {
            if (it != null) {
                when_travel_edit_text.setText(createStringFromRangeDates(it))
            }
        })
        newTravelsViewModel.currencyText.observe(this, Observer {
            if (it != null) {
                currency_travel_edit_text.setText(it)
                validate()
            }
        })
        currency_travel_edit_text.setOnFocusChangeListener { view, b ->
            if (b) {//переход на фрагмент выбора валюты
                findNavController().navigate(R.id.action_newTravelFragment_to_currencyFragment)
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        validate()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    private fun validate() {
        val isWheeEmpty = where_travel_edit_text.text?.isEmpty() ?: true
        val isWhenEmpty = when_travel_edit_text.text?.isEmpty() ?: true
        val isWhoEmpty = who_travel_edit_text.text?.isEmpty() ?: true
        val isCurrencyEmpty = currency_travel_edit_text.text?.isEmpty() ?: true
        button_new_travel_save.isEnabled =
            !isWheeEmpty && !isWhenEmpty && !isWhoEmpty && !isCurrencyEmpty
    }
}