package com.twosmalpixels.travel_notes.core.offline_mode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import java.io.File

interface IOflineModeUseCase {
    fun getModeLiveData(): MutableLiveData<Boolean>
    fun setOflineMode(isOfline: Boolean)
    fun hasOfflineMode(): Boolean
    fun saveToRemoteLocalyData(
        db: FirebaseFirestore,
        storage: FirebaseStorage,
        lifecycleOwner: LifecycleOwner,
        writeCloudListener: WriteCloudListener,
        file: File
    )
}