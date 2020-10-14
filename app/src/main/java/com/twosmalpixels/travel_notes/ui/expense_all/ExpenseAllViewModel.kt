package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.ui.all_currency.IAllCurrencyUseCase
import com.twosmalpixels.travel_notes.ui.currency.ICurrencyUseCase
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import com.twosmalpixels.travel_notes.views.shedule.SheduleData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData
import org.koin.java.standalone.KoinJavaComponent

class ExpenseAllViewModel: ViewModel() {

    private val iAddExpenceUseCase by KoinJavaComponent.inject(
        IExpenceAllUseCase::class.java)

    private val iAllCurrencyUseCase by KoinJavaComponent.inject(
        IAllCurrencyUseCase::class.java)

    private val iCurUseCase by KoinJavaComponent.inject(
        ICurrencyUseCase::class.java)

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

    fun getCurrencyList(): ArrayList<CurrencyData>{
        return iCurUseCase.createCurrensyListFromItem(travelsItem, iAllCurrencyUseCase.getAllCurrency(0))
    }

    fun getExpenceList(db: FirebaseFirestore): MutableLiveData<ArrayList<ExpenceData>>{
        return iAddExpenceUseCase.getExpenceList(db, travelsItem.docName)
    }

}