package com.twosmalpixels.travel_notes.core

import android.location.Location

fun createStringFromLocation(location: Location): String{
    return "lat = ${location.getLatitude()}, lon = ${location.getLongitude()}"
}