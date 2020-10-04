package com.twosmalpixels.travel_notes.ui.new_travel

import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem

interface INewTravelsRepositoriy {

    fun saveNewTravelData(db: FirebaseFirestore, travelsItem: TravelsItem, writeCloudListener: WriteCloudListener)


}