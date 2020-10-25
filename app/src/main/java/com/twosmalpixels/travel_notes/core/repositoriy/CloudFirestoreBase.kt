package com.twosmalpixels.travel_notes.core.repositoriy

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.offline_mode.IOflineModeUseCase
import com.twosmalpixels.travel_notes.ui.auth.IAuthUseCase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class CloudFirestoreBase(
    val iAuthUseCase: IAuthUseCase,
    val oflineModeUseCase: IOflineModeUseCase
) : ICloudFirestoreBase {

    override suspend fun getAllDocumentInCollections(
        db: FirebaseFirestore,
        collection: String
    ): List<DocumentSnapshot?> {
        if (iAuthUseCase.getUserId() != null) {
            return try {
                val rez = db.collection(USERS_COLLECTION)
                    .document(iAuthUseCase.getUserId()!!)
                    .collection(collection)
                    .get()
                    .await()
                rez.documents
            } catch (e: Exception) {
                oflineModeUseCase.setOflineMode(true)
                arrayListOf<DocumentSnapshot?>()
            }
        } else {
            return arrayListOf<DocumentSnapshot?>()
        }
    }

    override suspend fun getAllDocumentInExpenceCollections(
        db: FirebaseFirestore,
        doc: String
    ): List<DocumentSnapshot?> {
        if (iAuthUseCase.getUserId() != null) {
            return try {
                val rez = db.collection(USERS_COLLECTION)
                    .document(iAuthUseCase.getUserId()!!)
                    .collection(TRAVELS_COLLECTION)
                    .document(doc)
                    .collection(EXPENCE_COLLECTION)
                    .get()
                    .await()
                rez.documents
            } catch (e: Exception) {
                oflineModeUseCase.setOflineMode(true)
                arrayListOf<DocumentSnapshot?>()
            }
        } else {
            return arrayListOf<DocumentSnapshot?>()
        }
    }
}