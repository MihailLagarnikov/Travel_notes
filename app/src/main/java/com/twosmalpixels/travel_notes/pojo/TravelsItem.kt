package com.twosmalpixels.travel_notes.pojo

import android.content.res.Resources
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.getFireLong
import com.twosmalpixels.travel_notes.core.extension.getFireString
import com.twosmalpixels.travel_notes.core.repositoriy.InnerImage
import com.twosmalpixels.travel_notes.core.repositoriy.TravelsCollection

data class TravelsItem(val title: String, val data: String,val dataStart: Long, val dataEnd: Long, val person: String, val imageUrl: String) {
    
    companion object {
         fun createFromMap(map: Map<String, Any>): TravelsItem {
            return TravelsItem(
                map.getFireString(TravelsCollection.TITLE.name),
                map.getFireString(TravelsCollection.DATA_STRING.name),
                map.getFireLong(TravelsCollection.DATA_START.name),
                map.getFireLong(TravelsCollection.DATA_END.name),
                map.getFireString(TravelsCollection.PERSON.name),
                map.getFireString(TravelsCollection.IMAGE.name)
                
            )
        }

        fun createMap(travelsItem: TravelsItem): Map<String, Any>{
            val rezult = HashMap<String, Any>()
            rezult.set(TravelsCollection.TITLE.name, travelsItem.title)
            rezult.set(TravelsCollection.DATA_STRING.name, travelsItem.data)
            rezult.set(TravelsCollection.DATA_STRING.name, travelsItem.data)
            rezult.set(TravelsCollection.DATA_START.name, travelsItem.dataStart)
            rezult.set(TravelsCollection.DATA_END.name, travelsItem.dataEnd)
            rezult.set(TravelsCollection.PERSON.name, travelsItem.person)
            rezult.set(TravelsCollection.IMAGE.name, travelsItem.imageUrl)
            return rezult
        }

        fun createDefaultItem(resources: Resources): TravelsItem{
            return TravelsItem(resources.getString(R.string.add_new_travel), "", 0L, 0L,"", InnerImage.IMG_ADD_NEW_TRAVEL.img)
        }
    }

  
}