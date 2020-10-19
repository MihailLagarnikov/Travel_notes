package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.views.shedule.CategoryExpenceData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData

interface IExpenceAllUseCase {
    fun getDefaultShdulelistSpiner(travelsItem: TravelsItem): ArrayList<SheduleSpinerData>
    fun getDefaultExpenseList(): ArrayList<ExpenceData>
    fun getExpenceList(db: FirebaseFirestore, docName: String): MutableLiveData<ArrayList<ExpenceData>>
    fun getTotalExpenceWithCurrency (allExpenceList: ArrayList<ExpenceData>, travelsItem: TravelsItem): ArrayList<SheduleSpinerData>
    fun getCategoryExpenceList(allExpenceList: ArrayList<ExpenceData>, travelsItem: TravelsItem): ArrayList<CategoryExpenceData>
}