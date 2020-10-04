package com.twosmalpixels.travel_notes.ui.new_travel

import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.TRAVELS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.USERS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.auth.AuthInterface

class NewTravelsRepositoiy( val authInterface: AuthInterface): INewTravelsRepositoriy {

    override fun saveNewTravelData(db: FirebaseFirestore, travelsItem: TravelsItem, writeCloudListener: WriteCloudListener) {
        db.collection(USERS_COLLECTION)
            .document(authInterface.getUserId()!!)
            .collection(TRAVELS_COLLECTION)
            .add(TravelsItem.createMap(travelsItem))

            .addOnSuccessListener { documentReference ->
                writeCloudListener.setSuccess(true)
            }
            .addOnFailureListener { e ->
                writeCloudListener.setSuccess(false)
            }
    }


}