package com.twosmalpixels.travel_notes.ui.share_expence

import android.content.res.Resources
import android.graphics.Bitmap
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy.ILocalRepositoriy
import com.twosmalpixels.travel_notes.ui.calendar_dialog.createStringFromDateAndTime
import org.koin.java.standalone.KoinJavaComponent
import java.io.File
import java.util.*

class ShareUseCase() : IShareUseCase {

    private val localRepositoriy: ILocalRepositoriy by KoinJavaComponent.inject(ILocalRepositoriy::class.java)

    override fun saveBitmapShare(
        bitmap: Bitmap,
        file: File,
        resources: Resources,
        travelName: String
    ): File {
        val fName = getNameShareFile(resources, travelName)
        val filePng = localRepositoriy.saveBitmap(bitmap, fName, file)

        return filePng
    }

    private fun getNameShareFile(resources: Resources, travelName: String): String {
        val appName = resources.getString(R.string.app_name)
        val dataText = createStringFromDateAndTime(Date().time)
        return "${appName}_${travelName}_${dataText}"
    }
}