package com.twosmalpixels.travel_notes.views.shedule

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import kotlinx.android.synthetic.main.shedule_view.view.*


class SheduleView: ConstraintLayout, AdapterView.OnItemSelectedListener {

    private val HEIGHT_PERSENT = 0.25
    private lateinit var sheduleAdapter: SheduleAdapter
    private val listSpinerData = ArrayList<SheduleSpinerData>()
    private var curentAmount = 0
    private var categoryExpenceData: CategoryExpenceData? = null

    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.shedule_view, this)
        sheduleAdapter = SheduleAdapter(getMaxShrduleHeight(), {chooseItem(it)})
        expense_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        expense_recycler.adapter = sheduleAdapter

        curency_spiner.adapter = SheduleSpinerAdapter(context, R.layout.spiner_shedule_view, arrayOf("1", "2"), listSpinerData)
        curency_spiner.setSelection(curentAmount)
        curency_spiner.onItemSelectedListener = this
    }

    fun setListSheduleData(newList: ArrayList<CategoryExpenceData>, isStartState: Boolean){
        sheduleAdapter.setListData(newList, isStartState)
    }

    fun isStartState(isStartState: Boolean){
        sheduleAdapter.setStartSte(isStartState)
    }

    fun setListSpinerData(newList: ArrayList<SheduleSpinerData>){
        (curency_spiner.adapter as SheduleSpinerAdapter).setNewlist(newList)
        curency_spiner.isEnabled = newList.size != 1
    }

    fun chooseItem(categoryExpenceData: CategoryExpenceData){
        this.categoryExpenceData = categoryExpenceData
        expense_name.visibility = View.VISIBLE
        expense_value.visibility = View.VISIBLE
        expense_curency.visibility = View.VISIBLE
        expense_name.setText(categoryExpenceData.name)
        expense_value.text =
            if (curency_spiner.selectedItemPosition == 0){
            categoryExpenceData.mainCurrAmount.toString()
        }else{
            categoryExpenceData.additionalCurrAmount.toString()
        }
        expense_curency.text =
            if (curency_spiner.selectedItemPosition == 0){
                categoryExpenceData.mainCurrencyIso
            }else{
                categoryExpenceData.additionalCurrencyIso
            }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //изменили валюту
        curentAmount = p2
        if (categoryExpenceData != null){
            chooseItem(categoryExpenceData!!)
        }

    }

    private fun getMaxShrduleHeight():Int{
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return (size.y * HEIGHT_PERSENT).toInt()

    }

}