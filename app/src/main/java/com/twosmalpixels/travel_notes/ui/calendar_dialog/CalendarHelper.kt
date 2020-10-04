package com.twosmalpixels.travel_notes.ui.calendar_dialog

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private val DATE_FORMAT = "dd-MM-yy"
private val DEFIS = " - "
private val EMPTY = ""

fun createStringFromRangeDates(listDates: ArrayList<Date>?): String{
    return if (listDates != null && listDates.size >= 2 ){
        val dateFormat: DateFormat = SimpleDateFormat(DATE_FORMAT)
        dateFormat.format(listDates.get(0)) + DEFIS + dateFormat.format(listDates.get(1))
    }else{
        EMPTY
    }

}

fun createStringFromDate(long: Long): String{
    val date = Date(long)
    val dateFormat: DateFormat = SimpleDateFormat(DATE_FORMAT)
    return dateFormat.format(date)
}

fun getCurentDate(): Long{
    val calendar = Calendar.getInstance()
    return  calendar.timeInMillis
}