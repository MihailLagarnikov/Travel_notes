package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import org.koin.java.standalone.KoinJavaComponent.inject

class YouTravelsViewModel: ViewModel() {

    private val useCase: IYouTravelsUseCase by inject(IYouTravelsUseCase::class.java)
    var isFirstVisitYouTravelsFragment = true

    fun getYouTravelsList(db: FirebaseFirestore): MutableLiveData<ArrayList<TravelsItem>> {
        return useCase.getYouTravelsList(db)
    }

    fun hasTravelNow(travelsItem: ArrayList<TravelsItem>): TravelsItem?{
        return useCase.hasTravelNow(travelsItem)
    }
}