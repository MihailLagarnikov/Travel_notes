package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem

interface IYouTravelsUseCase {
    fun getYouTravelsList(db: FirebaseFirestore, isOffline: Boolean): MutableLiveData<ArrayList<TravelsItem>>
    fun getLocalyTravelsList(): ArrayList<TravelsItem>
    fun hasTravelNow(travelsItem: ArrayList<TravelsItem>): TravelsItem?
}