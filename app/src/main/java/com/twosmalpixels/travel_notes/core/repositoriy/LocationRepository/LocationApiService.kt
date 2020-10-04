package com.twosmalpixels.travel_notes.core.repositoriy.LocationRepository

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

interface LocationApiService {

    @GET("point")
    fun search(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("houses") houses: Boolean
    ): Call<MutableList<LocationData>>

    object RetrofitClient {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://whatsthere.maps.sputnik.ru/"

        fun getClient(): LocationApiService {
            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(LocationApiService::class.java)
        }
    }
}