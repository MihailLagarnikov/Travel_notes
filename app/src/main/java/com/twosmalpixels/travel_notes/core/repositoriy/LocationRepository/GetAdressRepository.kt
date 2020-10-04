package com.twosmalpixels.travel_notes.core.repositoriy.LocationRepository

import retrofit2.Call

class GetAdressRepository(val locationApiService: LocationApiService) {
    fun searchAdress(lat: Double, lon: Double): Call<MutableList<LocationData>> {
        return locationApiService.search(lat, lon, true)
    }
}