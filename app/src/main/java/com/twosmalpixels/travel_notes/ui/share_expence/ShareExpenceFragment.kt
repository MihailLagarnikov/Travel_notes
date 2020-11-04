package com.twosmalpixels.travel_notes.ui.share_expence

import BaseFragment
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.WindowManager
import androidx.core.content.PermissionChecker.checkCallingOrSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.MainActivity
import kotlinx.android.synthetic.main.share_expence_fragment.*
import java.io.File


class ShareExpenceFragment : BaseFragment() {

    private val DELAY_OPEN = 500L
    private val NUUL_AMOUNT = "0"
    private val WEIGHT_PERSENT = 0.3
    private val PERMISSION_REQUEST_CODE = 443

    override fun getToolbarParam() = ToolbarParam(getString(R.string.share_expence))

    override fun getLayout() = R.layout.share_expence_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!hasPermissions()) {
            requestPerms()
        }

        super.onViewCreated(view, savedInstanceState)
        recycler_share.layoutManager = LinearLayoutManager(context)
        val adapter = RecyclerShareAdapter(getMaxShareWeight(), expenseAllViewModel.currencyToShare)
        recycler_share.adapter = adapter

        expenseAllViewModel.getExpenceList((requireActivity() as MainActivity).db)
            .observe(this, Observer {
                if (it != null && !it.isEmpty()) {
                    adapter.setListData(expenseAllViewModel.getCategoryExpenceList(it))
                    expenseAllViewModel.setTotalExpenceWithCurrency(it)
                }
            })

        expenseAllViewModel.curencyDataList.observe(this, Observer {
            if (it != null && !it.isEmpty()) {
                val amount =
                    if (expenseAllViewModel.currencyToShare.equals(it.get(0).currencyIso)) {
                        it.get(0).amount.toString()
                    } else if (expenseAllViewModel.currencyToShare.equals(it.get(1).currencyIso)) {
                        it.get(1).amount.toString()
                    } else {
                        NUUL_AMOUNT
                    }
                total_amount_share.text = amount
            }
        })

        setTitleText(expenseAllViewModel.travelsItem)
        total_currency_share.text = expenseAllViewModel.currencyToShare

        button_share.setOnClickListener {
            val viewModel = ViewModelProviders.of(requireActivity()).get(ShareViewModel::class.java)
            val fileImage =
                viewModel.saveBitmapShareAndOpen(
                    getBitmapFromView(
                        share_card_view
                    )!!,
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    resources,
                    expenseAllViewModel.travelsItem.title
                )

            val uri = Uri.fromFile(fileImage)
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri)
            requireActivity().sendBroadcast(intent)

            val intentOpen = Intent(Intent.ACTION_VIEW)
            intentOpen.setDataAndType(uri, "image/*")
            startActivity(intentOpen)
        }
    }

    private fun setTitleText(travelItem: TravelsItem) {
        val titleText = "${travelItem.title} /"
        title_where_share_text.text = titleText
        title_who_share_text.text = travelItem.person
        title_when_end_share_text.text = travelItem.data
    }

    private fun getMaxShareWeight(): Int {
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return (size.x * WEIGHT_PERSENT).toInt()
    }

    fun getBitmapFromView(view: View): Bitmap? { //Define a bitmap with the same size as the view
        val returnedBitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null)
            bgDrawable.draw(canvas) else
            canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    private fun hasPermissions(): Boolean {
        var res = 0
        //string array of permissions,
        val permissions =
            arrayOf<String>(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        for (perms in permissions) {
            res = checkCallingOrSelfPermission(requireContext(), perms)
            if (res != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestPerms() {
        val permissions =
            arrayOf<String>(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        var allowed = true
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> for (res in grantResults) {
                allowed = allowed && res == PackageManager.PERMISSION_GRANTED
            }
            else -> allowed = false
        }
        if (!allowed) {
        }
    }
}