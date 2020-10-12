package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.views.shedule.SheduleData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData
import org.koin.java.standalone.KoinJavaComponent

class ExpenseAllViewModel: ViewModel() {
    private val iAddExpenceUseCase by KoinJavaComponent.inject(
        IExpenceAllUseCase::class.java)
    var travelsItem: TravelsItem = TravelsItem.createEmptyItem()
    set(value) {
        curencyDataList.value = iAddExpenceUseCase.getDefaultShdulelistSpiner(travelsItem)
        field = value
    }

    val sheduleDataList = MutableLiveData<ArrayList<SheduleData>>()
    val curencyDataList = MutableLiveData<ArrayList<SheduleSpinerData>>()
    val expenceDataList = MutableLiveData<ArrayList<ExpenceData>>()
    var toolbareName: String = ""

    init {
        sheduleDataList.value = iAddExpenceUseCase.getDefaultShdulelist()
        curencyDataList.value = iAddExpenceUseCase.getDefaultShdulelistSpiner(travelsItem)
        expenceDataList.value = iAddExpenceUseCase.getDefaultExpenseList()
    }
}