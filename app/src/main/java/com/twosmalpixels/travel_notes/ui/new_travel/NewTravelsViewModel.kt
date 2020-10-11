package com.twosmalpixels.travel_notes.ui.new_travel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import org.koin.java.standalone.KoinJavaComponent
import java.util.*
import kotlin.collections.ArrayList

class NewTravelsViewModel: ViewModel(), WriteCloudListener {
    var isRangeMode = true
    private val iNewTravelsUseCase: INewTravelsUseCase by KoinJavaComponent.inject(INewTravelsUseCase::class.java)
    val changeStatus = MutableLiveData<Boolean>()
    val chooseDates = MutableLiveData<ArrayList<Date>>()
    val chooseDate = MutableLiveData<Date>()
    val currencyText = MutableLiveData<String>()
    var mainCurrencyCode: String = ""
    var additionalCurrencyCode: String = ""
    var rates: Int = 0

    fun saveNewTravelData(db: FirebaseFirestore, travelsItem: TravelsItem){
        iNewTravelsUseCase.saneNewTravelData(db, travelsItem, this)
    }

    fun saveBitmap(bitmap: Bitmap, name: String, storage: FirebaseStorage){
        iNewTravelsUseCase.saveSkin(bitmap, name, storage)
    }

    override fun setSuccess(isSuccess: Boolean) {
        changeStatus.value = isSuccess
    }
}