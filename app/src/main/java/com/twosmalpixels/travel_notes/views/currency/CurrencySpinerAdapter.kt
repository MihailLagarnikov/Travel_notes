package com.twosmalpixels.travel_notes.views.currency

import android.content.Context
import android.widget.ArrayAdapter
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData

class CurrencySpinerAdapter(context: Context,
                            val resource: Int,
                            objects: Array<out String>,
                            val listData: ArrayList<SheduleSpinerData>) :
    ArrayAdapter<String>(context, resource, objects) {
}