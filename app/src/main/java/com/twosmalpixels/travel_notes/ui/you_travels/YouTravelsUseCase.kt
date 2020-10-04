package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem

class YouTravelsUseCase(val iYouTravelsRepositoriy: IYouTravelsRepositoriy): IYouTravelsUseCase {

    override fun getYouTravelsList(db: FirebaseFirestore): MutableLiveData<ArrayList<TravelsItem>> {
        return iYouTravelsRepositoriy.getYouTravelsList(db)
    }
}