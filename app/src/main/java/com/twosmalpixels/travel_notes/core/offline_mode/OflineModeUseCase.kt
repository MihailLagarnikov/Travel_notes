package com.twosmalpixels.travel_notes.core.offline_mode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.HASE_OFFLINE_MODE
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.HASE_SAVE_EXPENCE_DATA
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.ISharedPrefHelper
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.TRAVEL_NAME_WHAT_WE_SAVED_EXPENCE
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.IExpenceRepository
import com.twosmalpixels.travel_notes.ui.you_travels.IYouTravelsRepositoriy

class OflineModeUseCase(
    val sharedPrefHelper: ISharedPrefHelper,
    val youTravelsRepositoriy: IYouTravelsRepositoriy,
    val expenceRepository: IExpenceRepository
) : IOflineModeUseCase {
    private val isOfline = MutableLiveData<Boolean>()

    override fun getModeLiveData(): MutableLiveData<Boolean> {
        return isOfline
    }

    override fun setOflineMode(isOfline: Boolean) {
        this.isOfline.value = isOfline
        if (isOfline) {
            sharedPrefHelper.saveBoolean(HASE_OFFLINE_MODE, true)
        }
    }

    override fun hasOfflineMode(): Boolean {
        return !(isOfline.value ?: false) && sharedPrefHelper.loadBoolean(HASE_OFFLINE_MODE, false)
    }

    override fun saveToRemoteLocalyData(db: FirebaseFirestore, lifecycleOwner: LifecycleOwner) {
        val hasSavedExpenceData = sharedPrefHelper.loadBoolean(HASE_SAVE_EXPENCE_DATA, false)
        val travelName = sharedPrefHelper.loadText(TRAVEL_NAME_WHAT_WE_SAVED_EXPENCE, "")
        if (hasSavedExpenceData) {
            youTravelsRepositoriy.getYouTravelsList(db, false).observe(lifecycleOwner, Observer {
                if (it != null && !it.isEmpty()) {
                    val notSavedTravel = findNotSavedTravel(it)
                    if (notSavedTravel != null) {
                        expenceRepository.saveExpenceData(db)
                    }
                }
            })

        }
    }

    private fun findNotSavedTravel(travelList: ArrayList<TravelsItem>): TravelsItem? {
        val travelName = sharedPrefHelper.loadText(TRAVEL_NAME_WHAT_WE_SAVED_EXPENCE, "")
        for (item in travelList) {
            if (travelName.equals(item.title + item.data)) {
                return item
            }
        }
        return null
    }
}