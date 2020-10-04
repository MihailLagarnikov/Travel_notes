package com.twosmalpixels.travel_notes.ui.new_travel

import android.graphics.Bitmap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.core.repositoriy.IFairbaseStorageBase
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.choose_travel_skin.IChooseSkinUseCase
import org.koin.java.standalone.KoinJavaComponent

class NewTravelsUseCase(val iNewTravelsRepositoriy: INewTravelsRepositoriy): INewTravelsUseCase {
    private val iFairbaseStorageBase by KoinJavaComponent.inject(
        IFairbaseStorageBase::class.java
    )


    override fun isInnerImage(nameImage: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveImageInFireStorage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saneNewTravelData(db: FirebaseFirestore, travelsItem: TravelsItem, writeCloudListener: WriteCloudListener) {
        iNewTravelsRepositoriy.saveNewTravelData(db, travelsItem, writeCloudListener)
    }

    override fun saveSkin(bitmap: Bitmap, name: String, storage: FirebaseStorage) {
        iFairbaseStorageBase.saveBitmap(bitmap, name, storage)
    }
}