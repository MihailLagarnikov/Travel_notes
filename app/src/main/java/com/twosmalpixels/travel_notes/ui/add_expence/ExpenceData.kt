package com.twosmalpixels.travel_notes.ui.add_expence

import com.twosmalpixels.travel_notes.core.extension.getFireDouble
import com.twosmalpixels.travel_notes.core.extension.getFireLong
import com.twosmalpixels.travel_notes.core.extension.getFireString
import com.twosmalpixels.travel_notes.core.repositoriy.ExpenceDataCollection

data class ExpenceData(
    val amount: Long,
    val currencyIso: String,
    val category: Long,
    val comment: String,
    val data: String,
    val dataLong: Long,
    val imageUrl: String,
    val lat: Double,
    val lon: Double
) {

    companion object{

        const val NON_IMAGE = "0"

        fun createFromMap(map: Map<String, Any>): ExpenceData {
            return ExpenceData(
                map.getFireLong(ExpenceDataCollection.AMOUNT.name),
                map.getFireString(ExpenceDataCollection.CURRENCY.name),
                map.getFireLong(ExpenceDataCollection.CATEGORY.name),
                map.getFireString(ExpenceDataCollection.COMMENT.name),
                map.getFireString(ExpenceDataCollection.DATA.name),
                map.getFireLong(ExpenceDataCollection.DATA_LONG.name),
                map.getFireString(ExpenceDataCollection.IMAGEURL.name),
                map.getFireDouble(ExpenceDataCollection.LAT.name),
                map.getFireDouble(ExpenceDataCollection.LON.name)
            )
        }

        fun createMap(expData: ExpenceData): Map<String, Any> {
            val rezult = HashMap<String, Any>()
            rezult.set(ExpenceDataCollection.AMOUNT.name, expData.amount)
            rezult.set(ExpenceDataCollection.CURRENCY.name, expData.currencyIso)
            rezult.set(ExpenceDataCollection.CATEGORY.name, expData.category)
            rezult.set(ExpenceDataCollection.COMMENT.name, expData.comment)
            rezult.set(ExpenceDataCollection.DATA.name, expData.data)
            rezult.set(ExpenceDataCollection.DATA_LONG.name, expData.dataLong)
            rezult.set(ExpenceDataCollection.IMAGEURL.name, expData.imageUrl)
            rezult.set(ExpenceDataCollection.LAT.name, expData.lat)
            rezult.set(ExpenceDataCollection.LON.name, expData.lon)
            return rezult
        }

        fun getEmptyData(): ExpenceData{
            return ExpenceData(0,
                "",
                0,
                "",
                "",
                0,
                "",
                0.0,
                0.0)
        }
    }
}