package com.twosmalpixels.travel_notes.ui.add_expence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.ui.expense_all.ExpenceCategory
import kotlinx.android.synthetic.main.expence_item.view.*

class AllExpenceAdapter(val listCategoriys: Array<ExpenceCategory>, val listener: (ExpenceCategory?) -> Unit): RecyclerView.Adapter<AllExpenceAdapter.ViewHolder>() {
    private var selectedItem = 100
    private var laseSelectedCategory: ExpenceCategory? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expence_item, parent, false))
    }

    override fun getItemCount() = listCategoriys.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            logo_expence.setImageResource(listCategoriys.get(position).logoBlack)
            text_expenxe_item.setText(listCategoriys.get(position).text)
            setOnClickListener {
                val selectedCategory = if (listCategoriys.get(position) == laseSelectedCategory){
                    null
                }else{
                    listCategoriys.get(position)
                }
                laseSelectedCategory = selectedCategory
                selectedItem = position
                holder.selectedItemColor(position == selectedItem)
                listener.invoke(selectedCategory)
                notifyDataSetChanged()
            }
            holder.selectedItemColor(position == selectedItem)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun selectedItemColor(isSelected: Boolean){
            itemView.run {
                constraint_expence_item.setBackgroundColor(context.resources.getColor(getColorItem(!isSelected)))
                text_expenxe_item.setTextColor(context.resources.getColor(getColorItem(isSelected)))
            }

        }

        private fun getColorItem(isSelected: Boolean): Int{
            return if (isSelected){
                android.R.color.white
            }else {
                R.color.text_black
            }
        }
    }
}