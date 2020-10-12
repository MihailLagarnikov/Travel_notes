package com.twosmalpixels.travel_notes.ui.add_expence

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import org.koin.java.standalone.KoinJavaComponent

class AddExpenceViewModel: ViewModel() {
    val isHaveLocationPermision = MutableLiveData<Boolean>()
    private val iExpenceUseCase by KoinJavaComponent.inject(
        IExpenceUseCase::class.java
    )

    init {
        isHaveLocationPermision.value = false
    }

    fun saveExpenceData(db: FirebaseFirestore, expenceData: ExpenceData, travelsDocName: String, writeCloudListener: WriteCloudListener){
        iExpenceUseCase.saveExpenceData(db, expenceData, travelsDocName, writeCloudListener)
    }

}