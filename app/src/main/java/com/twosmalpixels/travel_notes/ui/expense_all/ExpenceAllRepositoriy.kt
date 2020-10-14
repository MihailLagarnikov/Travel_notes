package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.ICloudFirestoreBase
import com.twosmalpixels.travel_notes.core.repositoriy.DataSourse
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExpenceAllRepositoriy(val cloudFirestoreBase: ICloudFirestoreBase) : IExpenceAllRepositoriy {

    private val expenceDataList = MutableLiveData<ArrayList<ExpenceData>>()

    override fun getExpenceList(db: FirebaseFirestore, docName: String): MutableLiveData<ArrayList<ExpenceData>> {
        when (getSourceData()) {
            DataSourse.FAIRBASE -> getFairbaseExpenceList(db, docName)
            DataSourse.SHARE_PREF -> getSharedPrefExpenceList()
        }
        return expenceDataList
    }

    private fun getSourceData(): DataSourse {
        return DataSourse.FAIRBASE
    }

    private fun getFairbaseExpenceList(db: FirebaseFirestore, docName: String) {
        val rezult = ArrayList<ExpenceData>()
        GlobalScope.launch(Dispatchers.Main) {
            val services = async(Dispatchers.IO) {
                cloudFirestoreBase.getAllDocumentInExpenceCollections(
                    db,
                    docName
                )
            }
            services.await()
            val listDoc = services.getCompleted()
            if (listDoc.size != 0) {
                for (doc in listDoc) {
                    if (doc != null && doc.data != null) {
                        rezult.add(ExpenceData.createFromMap(doc.data!!))
                    }
                }
            }
            expenceDataList.value = rezult
        }
    }

    private fun getSharedPrefExpenceList(): ArrayList<ExpenceData> {
        return ArrayList<ExpenceData>()
    }
}