package com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy

import android.graphics.Bitmap
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import java.io.File

interface ILocalRepositoriy {

    fun saveNewTravel(travelsItem: TravelsItem)
    fun getAllTravels(): ArrayList<TravelsItem>
    fun saveExpenceData(expenceData: ExpenceData, travelsName: String)
    fun loadExpenceDataList(travelName: String): ArrayList<ExpenceData>
    fun saveBitmap(bitmap: Bitmap, name: String, file: File)
    fun loadBitmap(name: String, file: File): Bitmap
}