package com.twosmalpixels.travel_notes.ui.expense_all

import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.views.shedule.SheduleData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData
import kotlin.collections.ArrayList

class ExpenceAllAllUseCase: IExpenceAllUseCase {
    private val DEF_HEIGHT = 1
    private val SUM_NUL = 0
    private val DEF_LIST_AMOUNT = arrayListOf<Int>(SUM_NUL)
    private val DEF_LIST_CURENCY = arrayListOf<Int>(R.string.def_curensy)

    override fun getDefaultShdulelist(): ArrayList<SheduleData> {
        return arrayListOf(SheduleData(R.drawable.ic_icon_transport, DEF_HEIGHT, R.string.transport, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_home, DEF_HEIGHT, R.string.booking, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_cafe, DEF_HEIGHT, R.string.cafe_and_restorans, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_supermarket, DEF_HEIGHT, R.string.supermarket, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_tour, DEF_HEIGHT, R.string.tour, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_entertainment, DEF_HEIGHT, R.string.entertainment, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_gifts, DEF_HEIGHT, R.string.gifts, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_shopping, DEF_HEIGHT, R.string.shopping, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_safety, DEF_HEIGHT, R.string.safety, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_visa, DEF_HEIGHT, R.string.visa, DEF_LIST_AMOUNT, DEF_LIST_CURENCY),
            SheduleData(R.drawable.ic_icon_others, DEF_HEIGHT, R.string.others, DEF_LIST_AMOUNT, DEF_LIST_CURENCY)
            )
    }

    override fun getDefaultShdulelistSpiner(travelsItem: TravelsItem): ArrayList<SheduleSpinerData> {
        return arrayListOf(SheduleSpinerData(SUM_NUL, travelsItem.mainCurrencyIso),
            SheduleSpinerData(SUM_NUL, travelsItem.additionalCurrencyIso))
    }

    override fun getDefaultExpenseList(): ArrayList<ExpenceData> {
        return arrayListOf(getEmptyData())
    }
}