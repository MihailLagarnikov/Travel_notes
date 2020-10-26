package com.twosmalpixels.travel_notes.ui.add_expence

import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.EXPENCE_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy.ILocalRepositoriy
import com.twosmalpixels.travel_notes.core.repositoriy.TRAVELS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.USERS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import com.twosmalpixels.travel_notes.ui.auth.AuthInterface

class ExpenceRepository(val authInterface: AuthInterface, val localRepositoriy: ILocalRepositoriy) :
    IExpenceRepository {

    override fun saveExpenceData(
        db: FirebaseFirestore,
        expenceData: ExpenceData,
        travelsDocName: String,
        writeCloudListener: WriteCloudListener,
        isOffline: Boolean
    ) {
        if (isOffline) {
            saveLocaly(expenceData, travelsDocName, writeCloudListener)
        } else {
            saveFaireBase(db, expenceData, travelsDocName, writeCloudListener)
        }
    }

    private fun saveFaireBase(
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

    private fun saveLocaly(
        expenceData: ExpenceData,
        travelsDocName: String,
        writeCloudListener: WriteCloudListener
    ) {
        localRepositoriy.saveExpenceData(expenceData, travelsDocName)
        writeCloudListener.setSuccess(true)

    }
}