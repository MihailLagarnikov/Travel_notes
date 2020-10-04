package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem

interface IYouTravelsRepositoriy {
    fun getYouTravelsList(db: FirebaseFirestore): MutableLiveData<ArrayList<TravelsItem>>
}