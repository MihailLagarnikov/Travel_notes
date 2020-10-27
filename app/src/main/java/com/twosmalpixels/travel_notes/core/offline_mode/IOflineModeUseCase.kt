package com.twosmalpixels.travel_notes.core.offline_mode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

interface IOflineModeUseCase {
    fun getModeLiveData(): MutableLiveData<Boolean>
    fun setOflineMode(isOfline: Boolean)
    fun hasOfflineMode(): Boolean
    fun saveToRemoteLocalyData(db: FirebaseFirestore, lifecycleOwner: LifecycleOwner)
}