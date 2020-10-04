package com.twosmalpixels.travel_notes.ui.choose_travel_skin

import android.graphics.Bitmap
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.R
import org.koin.java.standalone.KoinJavaComponent

class ChooseSkinViewModel: ViewModel() {
    private val iChooseSkinUseCase: IChooseSkinUseCase by KoinJavaComponent.inject(
        IChooseSkinUseCase::class.java
    )
    val shoosenSkin = MediatorLiveData<Int>()
    val shoosenSkinBitmap = MediatorLiveData<Bitmap>()
    init {
        shoosenSkin.value = R.drawable.ic_img_new_travel_default
    }
    fun getSkinList(): ArrayList<Int> = iChooseSkinUseCase.getSkinList()

}