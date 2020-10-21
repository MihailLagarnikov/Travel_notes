package com.twosmalpixels.travel_notes.ui.add_expence

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import org.koin.java.standalone.KoinJavaComponent

class AddExpenceViewModel: ViewModel(), WriteCloudListener {

    val changeStatus = MutableLiveData<Boolean>()
    val isHaveLocationPermision = MutableLiveData<Boolean>()
    var lat: Double = 0.0
    var lon: Double = 0.0
    var currentCurrency: CurrencyData? = null

    private val iExpenceUseCase by KoinJavaComponent.inject(
        IExpenceUseCase::class.java
    )

    init {
        isHaveLocationPermision.value = false
    }

    fun saveExpenceData(db: FirebaseFirestore, expenceData: ExpenceData, travelsDocName: String){
        iExpenceUseCase.saveExpenceData(db, expenceData, travelsDocName, this)
    }

    override fun setSuccess(isSuccess: Boolean) {
        changeStatus.value = isSuccess
        changeStatus.value = null
    }
}