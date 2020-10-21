package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import java.util.*
import kotlin.collections.ArrayList

class YouTravelsUseCase(val iYouTravelsRepositoriy: IYouTravelsRepositoriy): IYouTravelsUseCase {

    override fun getYouTravelsList(db: FirebaseFirestore): MutableLiveData<ArrayList<TravelsItem>> {
        return iYouTravelsRepositoriy.getYouTravelsList(db)
    }

    override fun hasTravelNow(travelsItemsList: ArrayList<TravelsItem>): TravelsItem? {
        val timeNow = Date().time
        for (travelItem in travelsItemsList){
            if (timeNow >= travelItem.dataStart && timeNow < travelItem.dataEnd){
                return travelItem
            }
        }
        return null
    }
}