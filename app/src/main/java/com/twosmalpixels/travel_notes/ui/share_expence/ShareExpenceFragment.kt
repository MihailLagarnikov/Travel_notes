package com.twosmalpixels.travel_notes.ui.share_expence

import BaseFragment
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.View.MeasureSpec
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.convertToBitmap
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.MainActivity
import kotlinx.android.synthetic.main.share_expence_fragment.*
import kotlinx.android.synthetic.main.share_expence_fragment.share_card_view


class ShareExpenceFragment : BaseFragment() {

    private val NUUL_AMOUNT = "0"
    private val WEIGHT_PERSENT = 0.3

    override fun getToolbarParam() = ToolbarParam(getString(R.string.share_expence))

    override fun getLayout() = R.layout.share_expence_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            test_shet.setImageBitmap(share_card_view.convertToBitmap())
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

    fun getBitmapFromView(view: View): Bitmap? {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        return bitmap
    }
}