package com.twosmalpixels.travel_notes.views.shedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import kotlinx.android.synthetic.main.shedule_item.view.*

class SheduleAdapter(val maxHeight: Int,val listener: (CategoryExpenceData) -> Unit): RecyclerView.Adapter<SheduleAdapter.ViewHolder>() {
    private val listData = ArrayList<CategoryExpenceData>()

    fun setListData(newList: ArrayList<CategoryExpenceData>){
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shedule_item, parent, false))
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            image_shedule.setImageResource(listData.get(position).image)
            val params: ViewGroup.LayoutParams? = card_shedule.layoutParams
            params!!.height = listData.get(position).height * maxHeight
            card_shedule.layoutParams = params
            setOnClickListener {
                //клик
                listener.invoke(listData.get(position))
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}