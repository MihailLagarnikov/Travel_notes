package com.twosmalpixels.travel_notes.ui.share_expence

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import org.koin.java.standalone.KoinJavaComponent.inject
import java.io.File

class ShareViewModel : ViewModel() {

    private val shareUseCase: IShareUseCase by inject(IShareUseCase::class.java)

    fun saveBitmapShareAndOpen(
        bitmap: Bitmap,
        file: File,
        resources: Resources,
        travelName: String
    ): File {
        return shareUseCase.saveBitmapShare(bitmap, file, resources, travelName)
    }
}