package com.twosmalpixels.travel_notes.ui.add_expence

import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.WriteCloudListener
import org.koin.java.standalone.KoinJavaComponent

class ExpenceUseCase: IExpenceUseCase {

    private val iExpenceRepository by KoinJavaComponent.inject(
        IExpenceRepository::class.java
    )

    override fun saveExpenceData(
        db: FirebaseFirestore,
        expenceData: ExpenceData,
        travelsDocName: String,
        writeCloudListener: WriteCloudListener,
        isOffline: Boolean
    ) {
        iExpenceRepository.saveExpenceData(db, expenceData, travelsDocName, writeCloudListener, isOffline)
    }
}