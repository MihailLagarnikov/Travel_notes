package com.twosmalpixels.travel_notes.core.offline_mode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy.ILocalRepositoriy
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.*
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.IExpenceRepository
import com.twosmalpixels.travel_notes.ui.you_travels.IYouTravelsRepositoriy
import org.koin.java.standalone.KoinJavaComponent

class OflineModeUseCase(
    val sharedPrefHelper: ISharedPrefHelper,
    val expenceRepository: IExpenceRepository,
    val localRepositoriy: ILocalRepositoriy
) : IOflineModeUseCase {

    private val isOfline = MutableLiveData<Boolean>()
    private val youTravelsRepositoriy by KoinJavaComponent.inject(
        IYouTravelsRepositoriy::class.java
    )

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

    override fun saveToRemoteLocalyData(
        db: FirebaseFirestore,
        lifecycleOwner: LifecycleOwner,
        writeCloudListener: WriteCloudListener
    ) {
        val hasSavedExpenceData = sharedPrefHelper.loadBoolean(HASE_SAVE_EXPENCE_DATA, false)
        val travelName = sharedPrefHelper.loadText(TRAVEL_NAME_WHAT_WE_SAVED_EXPENCE, "")
        var onlyOne = true
        if (hasSavedExpenceData) {
            youTravelsRepositoriy.getYouTravelsList(db, false).observe(lifecycleOwner, Observer {
                if (it != null && !it.isEmpty() && onlyOne) {
                    onlyOne = false
                    val notSavedTravel = findNotSavedTravel(it)
                    if (notSavedTravel != null) {
                        val notSavedExrence = localRepositoriy.loadExpenceDataList(travelName)
                        if (!notSavedExrence.isEmpty()) {
                            for (exData in notSavedExrence) {
                                expenceRepository.saveExpenceData(
                                    db,
                                    exData,
                                    notSavedTravel.docName,
                                    writeCloudListener,
                                    false
                                )
                            }
                            sharedPrefHelper.saveBoolean(HASE_SAVE_EXPENCE_DATA, false)
                            sharedPrefHelper.saveBoolean(HASE_SAVE_EXPENCE_DATA, false)
                            sharedPrefHelper.saveInt(EXPENCE_NUMBER + travelName, 0)
                        }

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