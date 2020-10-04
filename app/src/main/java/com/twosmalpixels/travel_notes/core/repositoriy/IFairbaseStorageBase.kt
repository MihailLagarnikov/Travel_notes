package com.twosmalpixels.travel_notes.core.repositoriy

import android.graphics.Bitmap
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage

interface IFairbaseStorageBase {
    fun saveBitmap(bitmap: Bitmap, name: String, storage: FirebaseStorage)

    fun loadBitMap(name: String, storage: FirebaseStorage, imageView: ImageView)
}