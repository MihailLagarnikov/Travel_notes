package com.twosmalpixels.travel_notes.ui.you_travels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.repositoriy.ICloudFirestoreBase
import com.twosmalpixels.travel_notes.core.repositoriy.TRAVELS_COLLECTION
import com.twosmalpixels.travel_notes.core.repositoriy.DataSourse
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class YouTravelsRepositoriy(val cloudFirestoreBase: ICloudFirestoreBase): IYouTravelsRepositoriy {
    private val youTravelDataList = MutableLiveData<ArrayList<TravelsItem>>()
    
    override fun getYouTravelsList(db: FirebaseFirestore): MutableLiveData<ArrayList<TravelsItem>> {
         when (getSourceData()){
            DataSourse.FAIRBASE -> getFairbaseYouTravelsList(db)
            DataSourse.SHARE_PREF -> getSharedPrefYuoTravelsList()
        }
        return youTravelDataList
    }

    private fun getSourceData(): DataSourse{
        return DataSourse.FAIRBASE
    }

    private fun getFairbaseYouTravelsList(db: FirebaseFirestore){
        val rezult = ArrayList<TravelsItem>()
        GlobalScope.launch(Dispatchers.Main) {
            val services = async(Dispatchers.IO) {cloudFirestoreBase.getAllDocumentInCollections(db, TRAVELS_COLLECTION)}
            services.await()
            val listDoc = services.getCompleted()
            if (listDoc.size != 0){
                for (doc in listDoc){
                    if (doc != null && doc.data != null){
                        rezult.add(TravelsItem.createFromMap(doc.data!!, doc.reference.id))
                    }
                }
            }
            youTravelDataList.value = rezult
        }

    }

    private fun getSharedPrefYuoTravelsList():ArrayList<TravelsItem>{
        return ArrayList<TravelsItem>()
    }
}