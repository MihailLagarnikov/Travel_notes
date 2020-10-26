package com.twosmalpixels.travel_notes.ui.add_expence

import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener

interface IExpenceRepository {

    fun saveExpenceData(
        db: FirebaseFirestore,
        expenceData: ExpenceData,
        travelsDocName: String,
        writeCloudListener: WriteCloudListener,
        isOffline: Boolean
    )
}