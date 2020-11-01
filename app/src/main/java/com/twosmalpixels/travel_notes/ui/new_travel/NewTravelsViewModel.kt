package com.twosmalpixels.travel_notes.ui.new_travel

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.core.offline_mode.IOflineModeUseCase
import com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy.ILocalRepositoriy
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import org.koin.java.standalone.KoinJavaComponent
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class NewTravelsViewModel : ViewModel(), WriteCloudListener {
    var isRangeMode = true
    private val newTravelsUseCase: INewTravelsUseCase by KoinJavaComponent.inject(INewTravelsUseCase::class.java)
    private val localRepositoriy: ILocalRepositoriy by KoinJavaComponent.inject(ILocalRepositoriy::class.java)
    private val oflineModeUseCase: IOflineModeUseCase by KoinJavaComponent.inject(IOflineModeUseCase::class.java)
    val changeStatus = MutableLiveData<Boolean?>()
    val chooseDates = MutableLiveData<ArrayList<Date>>()
    val chooseDate = MutableLiveData<Date>()
    val currencyText = MutableLiveData<String>()
    var mainCurrencyCode: String = ""
    var additionalCurrencyCode: String = ""
    var rates: Int = 0

    fun saveNewTravelData(db: FirebaseFirestore, travelsItem: TravelsItem) {
        newTravelsUseCase.saneNewTravelData(db, travelsItem, this)
    }

    fun saveBitmap(imageView: ImageView, name: String, storage: FirebaseStorage, file: File) {
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        if (oflineModeUseCase.getModeLiveData().value ?: false) {
            localRepositoriy.saveBitmap(bitmap, name, file)
        } else {
            newTravelsUseCase.saveSkin(bitmap, name, storage)
        }
    }

    override fun setSuccess(isSuccess: Boolean) {
        changeStatus.value = isSuccess
        changeStatus.value = null
    }

    fun getRandomFileName(): String {
        return newTravelsUseCase.getRandomFileName()
    }

    fun saveLocalTravelItem(travelsItem: TravelsItem?) {
        if (travelsItem != null) {
            localRepositoriy.saveNewTravel(travelsItem)
        }
    }
}