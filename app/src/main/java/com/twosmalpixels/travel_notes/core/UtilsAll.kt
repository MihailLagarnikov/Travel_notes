package com.twosmalpixels.travel_notes.core

import android.location.Location
import java.lang.NumberFormatException

fun createStringFromLocation(location: Location): String{
    return "lat = ${location.getLatitude()}, lon = ${location.getLongitude()}"
}

fun createLongFromString(text: String): Long{
    return try {
        text.toLong()
    }catch (e: NumberFormatException){
        0L
    }
}