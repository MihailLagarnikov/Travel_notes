package com.twosmalpixels.travel_notes.views.shedule

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import kotlinx.android.synthetic.main.shedule_item.view.*
import kotlin.math.roundToInt

class SheduleAdapter(val maxHeight: Int, val listener: (CategoryExpenceData) -> Unit) :
    RecyclerView.Adapter<SheduleAdapter.ViewHolder>() {
    private val listData = ArrayList<CategoryExpenceData>()
    private var isStartState = true
    private var selectedPosition = 500

    fun setListData(newList: ArrayList<CategoryExpenceData>, isStartState: Boolean) {
        listData.clear()
        listData.addAll(newList)
        this.isStartState = isStartState
        notifyDataSetChanged()
    }

    fun setStartSte(isStartState: Boolean) {
        this.isStartState = isStartState
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shedule_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            image_shedule.setImageResource(listData.get(position).image)
            if (listData.get(position).height != 0F) {
                val params: ViewGroup.LayoutParams? = card_shedule.layoutParams
                var cardHeight = (listData.get(position).height * maxHeight).roundToInt()
                if (cardHeight < resources.getDimension(R.dimen.min_card_height).toInt()) {
                    cardHeight = resources.getDimension(R.dimen.min_card_height).toInt()
                }
                params!!.height = cardHeight
                card_shedule.layoutParams = params
                constrint_shedule.setBackgroundColor(getBacgroundCardColor(resources, position))

                val constrParams = constr_shedule.layoutParams
                constrParams.height = maxHeight
                constr_shedule.layoutParams = constrParams

            }
            this.setVisibility(listData.get(position).height != 0F)
            setOnClickListener {
                //клик
                listener.invoke(listData.get(position))
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    private fun getBacgroundCardColor(resources: Resources, position: Int): Int {
        return if (isStartState) {
            resources.getColor(android.R.color.white)
        } else if (selectedPosition == position) {
            resources.getColor(R.color.text_979)
        } else {
            resources.getColor(R.color.text_black)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}