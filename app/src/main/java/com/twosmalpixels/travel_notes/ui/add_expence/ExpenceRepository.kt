package com.twosmalpixels.travel_notes.ui.add_expence

import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.EXPENCE_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.TRAVELS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.USERS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.ui.auth.AuthInterface

class ExpenceRepository(val authInterface: AuthInterface): IExpenceRepository {

    override fun saveExpenceData(
        db: FirebaseFirestore,
        expenceData: ExpenceData,
        travelsDocName: String,
        writeCloudListener: WriteCloudListener
    ) {
        db.collection(USERS_COLLECTION)
            .document(authInterface.getUserId()!!)
            .collection(TRAVELS_COLLECTION)
            .document(travelsDocName)
            .collection(EXPENCE_COLLECTION)
            .add(ExpenceData.createMap(expenceData))

            .addOnSuccessListener { documentReference ->
                writeCloudListener.setSuccess(true)
            }
            .addOnFailureListener { e ->
                writeCloudListener.setSuccess(false)
            }
    }
}