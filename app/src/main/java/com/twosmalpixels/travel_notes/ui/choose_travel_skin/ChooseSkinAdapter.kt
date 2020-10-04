package com.twosmalpixels.travel_notes.ui.choose_travel_skin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import kotlinx.android.synthetic.main.choose_travel_skin_item.view.*

class ChooseSkinAdapter(val listImg: ArrayList<Int>, val listener: (Int) -> Unit): RecyclerView.Adapter<ChooseSkinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.choose_travel_skin_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listImg.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.skin_travel_image.setImageResource(listImg.get(position))
        holder.itemView.setOnClickListener { listener.invoke(listImg.get(position)) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}