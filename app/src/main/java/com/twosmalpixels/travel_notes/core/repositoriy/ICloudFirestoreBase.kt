package com.twosmalpixels.travel_notes.core.repositoriy

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

interface ICloudFirestoreBase {
    suspend fun getAllDocumentInCollections(db: FirebaseFirestore, collection: String): List<DocumentSnapshot?>

    suspend fun getAllDocumentInExpenceCollections(db: FirebaseFirestore, doc: String): List<DocumentSnapshot?>
}