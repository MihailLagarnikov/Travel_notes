package com.twosmalpixels.travel_notes.ui.add_expence

import com.twosmalpixels.travel_notes.core.extension.getFireLong
import com.twosmalpixels.travel_notes.core.extension.getFireString
import com.twosmalpixels.travel_notes.core.repositoriy.ExpenceDataCollection

data class ExpenceData(
    val amount: Long,
    val category: Long,
    val comment: String,
    val data: String,
    val dataStart: Long,
    val dataEnd: Long,
    val imageUrl: String,
    val lat: Long,
    val lon: Long
) {

    companion object{
        fun createFromMap(map: Map<String, Any>): ExpenceData {
            return ExpenceData(
                map.getFireLong(ExpenceDataCollection.AMOUNT.name),
                map.getFireLong(ExpenceDataCollection.CATEGORY.name),
                map.getFireString(ExpenceDataCollection.COMMENT.name),
                map.getFireString(ExpenceDataCollection.DATA.name),
                map.getFireLong(ExpenceDataCollection.DATASTART.name),
                map.getFireLong(ExpenceDataCollection.DATAEND.name),
                map.getFireString(ExpenceDataCollection.IMAGEURL.name),
                map.getFireLong(ExpenceDataCollection.LAT.name),
                map.getFireLong(ExpenceDataCollection.LON.name)
            )
        }

        fun createMap(expData: ExpenceData): Map<String, Any> {
            val rezult = HashMap<String, Any>()
            rezult.set(ExpenceDataCollection.AMOUNT.name, expData.amount)
            rezult.set(ExpenceDataCollection.CATEGORY.name, expData.category)
            rezult.set(ExpenceDataCollection.COMMENT.name, expData.comment)
            rezult.set(ExpenceDataCollection.DATA.name, expData.data)
            rezult.set(ExpenceDataCollection.DATASTART.name, expData.dataStart)
            rezult.set(ExpenceDataCollection.DATAEND.name, expData.dataEnd)
            rezult.set(ExpenceDataCollection.IMAGEURL.name, expData.imageUrl)
            rezult.set(ExpenceDataCollection.LAT.name, expData.lat)
            rezult.set(ExpenceDataCollection.LON.name, expData.lon)
            return rezult
        }
    }
}