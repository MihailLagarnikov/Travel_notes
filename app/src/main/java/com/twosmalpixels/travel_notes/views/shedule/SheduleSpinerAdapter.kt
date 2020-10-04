package com.twosmalpixels.travel_notes.views.shedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.twosmalpixels.travel_notes.R

class SheduleSpinerAdapter(context: Context,
                           val resource: Int,
                           objects: Array<out String>,
                           val listData: ArrayList<SheduleSpinerData>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    fun getMyView(position: Int, view: View?, parent: ViewGroup): View{
        val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        val amount = view.findViewById<TextView>(R.id.amount_shedule)
        val curency = view.findViewById<TextView>(R.id.curency_shedule)
        amount.text = listData.get(position).amount.toString()
        curency.setText(listData.get(position).currency)
        return view

    }

    fun setNewlist(newList: ArrayList<SheduleSpinerData>){
        listData.clear()
        listData.addAll(newList)

        notifyDataSetChanged()
    }
}