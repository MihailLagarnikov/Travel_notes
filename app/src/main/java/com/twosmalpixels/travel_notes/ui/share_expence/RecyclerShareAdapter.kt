package com.twosmalpixels.travel_notes.ui.share_expence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.shedule.CategoryExpenceData
import kotlinx.android.synthetic.main.share_item.view.*
import kotlin.math.roundToInt

class RecyclerShareAdapter(val maxWeight: Int, val currencyIso: String) :
    RecyclerView.Adapter<RecyclerShareAdapter.ViewHolder>() {

    private val listData = ArrayList<CategoryExpenceData>()

    fun setListData(newList: ArrayList<CategoryExpenceData>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.share_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            val categoryData = listData.get(position)
            if (categoryData.height != 0F) {
                val params: ViewGroup.LayoutParams? = share_card_view.layoutParams
                var cardWeight = (categoryData.height * maxWeight).roundToInt()
                if (cardWeight < resources.getDimension(R.dimen.min_card_weight).toInt()) {
                    cardWeight = resources.getDimension(R.dimen.min_card_weight).toInt()
                }
                params!!.width = cardWeight
                share_card_view.layoutParams = params

                text_category_share.setText(categoryData.name)
                amount_share.text = holder.getAmount(categoryData)
                currency_share.text = currencyIso
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun getAmount(categoryExpenceData: CategoryExpenceData): String {
            return if (currencyIso.equals(categoryExpenceData.mainCurrencyIso)) {
                categoryExpenceData.mainCurrAmount.toString()
            } else {
                categoryExpenceData.additionalCurrAmount.toString()
            }
        }

    }
}