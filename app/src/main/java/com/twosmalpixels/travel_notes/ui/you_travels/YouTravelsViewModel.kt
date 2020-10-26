package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.offline_mode.IOflineModeUseCase
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import org.koin.java.standalone.KoinJavaComponent
import org.koin.java.standalone.KoinJavaComponent.inject

class YouTravelsViewModel: ViewModel() {

    private val oflineModeUseCase by KoinJavaComponent.inject(
        IOflineModeUseCase::class.java
    )
    private val useCase: IYouTravelsUseCase by inject(IYouTravelsUseCase::class.java)
    var isFirstVisitYouTravelsFragment = true

    fun getYouTravelsList(db: FirebaseFirestore): MutableLiveData<ArrayList<TravelsItem>> {
        return useCase.getYouTravelsList(db, oflineModeUseCase.getModeLiveData().value ?: false)
    }

    fun hasTravelNow(travelsItem: ArrayList<TravelsItem>): TravelsItem?{
        return useCase.hasTravelNow(travelsItem)
    }

    fun getLocalyTravelsList(): ArrayList<TravelsItem>{
        return useCase.getLocalyTravelsList()
    }
}