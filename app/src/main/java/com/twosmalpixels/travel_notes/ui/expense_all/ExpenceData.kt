package com.twosmalpixels.travel_notes.ui.expense_all

data class ExpenceData(val category: Int,  val date: Long, val amount: Int, val curency: String, val comment: String, val image: String, val location: String) {
}

fun getEmptyData() = ExpenceData(0,0L,0,"", "","", "")