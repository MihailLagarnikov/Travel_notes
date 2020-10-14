package com.twosmalpixels.travel_notes.pojo

import android.content.res.Resources
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.getFireInt
import com.twosmalpixels.travel_notes.core.extension.getFireLong
import com.twosmalpixels.travel_notes.core.extension.getFireString
import com.twosmalpixels.travel_notes.core.repositoriy.InnerImage
import com.twosmalpixels.travel_notes.core.repositoriy.TravelsCollection

data class TravelsItem(
    val title: String,
    val data: String,
    val dataStart: Long,
    val dataEnd: Long,
    val person: String,
    val imageUrl: String,
    val mainCurrencyIso: String,
    val additionalCurrencyIso: String,
    val rates: Int,
    val docName: String = ""
) {

    companion object {
        fun createFromMap(map: Map<String, Any>, docName: String): TravelsItem {
            return TravelsItem(
                map.getFireString(TravelsCollection.TITLE.name),
                map.getFireString(TravelsCollection.DATA_STRING.name),
                map.getFireLong(TravelsCollection.DATA_START.name),
                map.getFireLong(TravelsCollection.DATA_END.name),
                map.getFireString(TravelsCollection.PERSON.name),
                map.getFireString(TravelsCollection.IMAGE.name),
                map.getFireString(TravelsCollection.MAIN_CURRENCY.name),
                map.getFireString(TravelsCollection.ADDITIONAL_CURRENCY.name),
                map.getFireInt(TravelsCollection.RATES_CURRENCY.name),
                docName

            )
        }

        fun createMap(travelsItem: TravelsItem): Map<String, Any> {
            val rezult = HashMap<String, Any>()
            rezult.set(TravelsCollection.TITLE.name, travelsItem.title)
            rezult.set(TravelsCollection.DATA_STRING.name, travelsItem.data)
            rezult.set(TravelsCollection.DATA_STRING.name, travelsItem.data)
            rezult.set(TravelsCollection.DATA_START.name, travelsItem.dataStart)
            rezult.set(TravelsCollection.DATA_END.name, travelsItem.dataEnd)
            rezult.set(TravelsCollection.PERSON.name, travelsItem.person)
            rezult.set(TravelsCollection.IMAGE.name, travelsItem.imageUrl)
            rezult.set(TravelsCollection.MAIN_CURRENCY.name, travelsItem.mainCurrencyIso)
            rezult.set(
                TravelsCollection.ADDITIONAL_CURRENCY.name,
                travelsItem.additionalCurrencyIso
            )
            rezult.set(TravelsCollection.RATES_CURRENCY.name, travelsItem.rates)
            return rezult
        }

        fun createDefaultItem(resources: Resources): TravelsItem {
            return TravelsItem(
                resources.getString(R.string.add_new_travel),
                "",
                0L,
                0L,
                "",
                InnerImage.IMG_ADD_NEW_TRAVEL.img,
                "",
                "",
                0,
                ""
            )
        }

        fun createEmptyItem(): TravelsItem {
            return TravelsItem(
                "",
                "",
                0L,
                0L,
                "",
                InnerImage.IMG_ADD_NEW_TRAVEL.img,
                "",
                "",
                0,
                ""
            )
        }
    }


}