package com.twosmalpixels.travel_notes.ui.expense_all

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import kotlinx.android.synthetic.main.expence_all_item.view.*
import kotlinx.android.synthetic.main.you_travels_item.view.*

class ExpenseAdapter(val listener: (ExpenceData) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ADD_NEW_ITEM = 1
    private val USUALY_ITEM = 2

    private val listDate = ArrayList<ExpenceData>()

    fun setNewList(newList: ArrayList<ExpenceData>){
        listDate.clear()
        listDate.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ADD_NEW_ITEM -> AddNewViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.you_travels_item,
                    parent,
                    false
                )
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.expence_all_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listDate.get(position).equals(ExpenceData.getEmptyData())) {
            ADD_NEW_ITEM
        } else {
            USUALY_ITEM
        }
    }

    override fun getItemCount() = listDate.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ExpenseAdapter.ViewHolder){
            holder.bindUsalyData(listDate.get(position))
        }else if (holder is ExpenseAdapter.AddNewViewHolder){
            holder.bindData()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindUsalyData(expenceData: ExpenceData){
            itemView.run {
                date_expence.text = expenceData.data
                logo_expence.setImageResource(getDrawableForNumber(expenceData.category.toInt()))
                title_expence.setText(getTextForNumber(expenceData.category.toInt()))
                amount_expence.text = expenceData.amount.toString()
                cur_expence.text = expenceData.currencyIso
                comment_expence.text = expenceData.comment
                comment_expence.setVisibility(expenceData.comment.isNotEmpty())
                itemView.setOnClickListener {
                    //редактируем запись
                }

            }

        }

    }

    inner class AddNewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindData(){
            itemView.title_item.setText(R.string.add_first_expense)
            itemView.setOnClickListener {
                //добавить новый расход
                listener.invoke(listDate.get(adapterPosition))
            }
            itemView.image_travel_item.setImageResource(R.drawable.ic_default_plus)
        }
    }


}