package com.twosmalpixels.travel_notes.ui.new_travel

import android.graphics.Bitmap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem

interface INewTravelsUseCase {

    fun isInnerImage(nameImage: String): Boolean

    fun saveImageInFireStorage()

    fun saneNewTravelData(db: FirebaseFirestore, travelsItem: TravelsItem, writeCloudListener: WriteCloudListener)

    fun saveSkin(bitmap: Bitmap, name: String, storage: FirebaseStorage)

    fun getRandomFileName(): String

}