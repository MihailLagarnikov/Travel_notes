package com.twosmalpixels.travel_notes.ui.share_expence

import android.content.res.Resources
import android.graphics.Bitmap
import java.io.File

interface IShareUseCase {

    fun saveBitmapShare(
        bitmap: Bitmap,
        file: File,
        resources: Resources,
        travelName: String
    ): File
}