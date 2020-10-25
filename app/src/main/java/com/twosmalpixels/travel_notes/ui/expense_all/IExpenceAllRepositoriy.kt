package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData

interface IExpenceAllRepositoriy {

    fun getExpenceList(
        db: FirebaseFirestore,
        docName: String,
        isOffline: Boolean
    ): MutableLiveData<ArrayList<ExpenceData>>
}