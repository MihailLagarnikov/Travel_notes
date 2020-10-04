package com.twosmalpixels.travel_notes.core.repositoriy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.ui.auth.IAuthUseCase
import io.opencensus.common.Scope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class FairbaseStorageBase(val iAuthUseCase: IAuthUseCase): IFairbaseStorageBase {
    private val SLASH = "/"
    private val ONE_MEGABYTE: Long = 1024 * 1024

    override fun saveBitmap(bitmap: Bitmap, name: String, storage: FirebaseStorage){
        GlobalScope.launch(Dispatchers.IO) {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val storageRef = storage.reference.child(createRefString(USERS_COLLECTION,iAuthUseCase.getUserId()!!, name))
            storageRef.putBytes(data)
        }
    }

    override fun loadBitMap(name: String, storage: FirebaseStorage, imageView: ImageView) {
        val storageRef = storage.reference.child(createRefString(USERS_COLLECTION,iAuthUseCase.getUserId()!!, name))
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0,it.size)
            imageView.setImageBitmap(bitmap)
        }.addOnFailureListener {
            imageView.setImageResource(InnerImage.LOAD_ERROR.drawable)
        }

    }

    private fun createRefString(vararg path: String): String{
        val rezult = StringBuilder()
        for (name in path){
            rezult.append(name)
            rezult.append(SLASH)
        }
        rezult.deleteCharAt(rezult.lastIndex)
        return rezult.toString()
    }
}