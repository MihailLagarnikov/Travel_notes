package com.twosmalpixels.travel_notes.ui.expense_all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.twosmalpixels.travel_notes.core.offline_mode.IOflineModeUseCase
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.ui.all_currency.IAllCurrencyUseCase
import com.twosmalpixels.travel_notes.ui.currency.ICurrencyUseCase
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import com.twosmalpixels.travel_notes.views.shedule.CategoryExpenceData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData
import org.koin.java.standalone.KoinJavaComponent

class ExpenseAllViewModel : ViewModel() {

    private val oflineModeUseCase by KoinJavaComponent.inject(
        IOflineModeUseCase::class.java
    )

    private val expenceAllUseCase by KoinJavaComponent.inject(
        IExpenceAllUseCase::class.java
    )

    private val iAllCurrencyUseCase by KoinJavaComponent.inject(
        IAllCurrencyUseCase::class.java
    )

    private val iCurUseCase by KoinJavaComponent.inject(
        ICurrencyUseCase::class.java
    )

    var travelsItem: TravelsItem = TravelsItem.createEmptyItem()
        set(value) {
            curencyDataList.value = expenceAllUseCase.getDefaultShdulelistSpiner(travelsItem)
            field = value
        }

    val categoryExpenceList = MutableLiveData<ArrayList<CategoryExpenceData>>()
    val curencyDataList = MutableLiveData<ArrayList<SheduleSpinerData>>()
    val expenceDataList = MutableLiveData<ArrayList<ExpenceData>>()
    var toolbareName: String = ""
    var currencyToShare: String = ""

    init {
        curencyDataList.value = expenceAllUseCase.getDefaultShdulelistSpiner(travelsItem)
        expenceDataList.value = expenceAllUseCase.getDefaultExpenseList()
    }

    fun getCurrencyList(): ArrayList<CurrencyData> {
        return iCurUseCase.createCurrensyListFromItem(
            travelsItem,
            iAllCurrencyUseCase.getAllCurrency(0)
        )
    }

    fun getExpenceList(
        db: FirebaseFirestore
    ): MutableLiveData<ArrayList<ExpenceData>> {
        return expenceAllUseCase.getExpenceList(db, travelsItem.docName, oflineModeUseCase.getModeLiveData().value ?: false)
    }

    fun setTotalExpenceWithCurrency(allExpenceList: ArrayList<ExpenceData>) {
        curencyDataList.value =
            expenceAllUseCase.getTotalExpenceWithCurrency(allExpenceList, travelsItem)
    }

    fun setCategoryExpenceList(allExpenceList: ArrayList<ExpenceData>) {
        categoryExpenceList.value =
            expenceAllUseCase.getCategoryExpenceList(allExpenceList, travelsItem)
    }

    fun getCategoryExpenceList(allExpenceList: ArrayList<ExpenceData>): ArrayList<CategoryExpenceData> {
        return expenceAllUseCase.getCategoryExpenceList(allExpenceList, travelsItem)
    }

    fun getDefaultExpenseList(): ArrayList<ExpenceData> {
        return expenceAllUseCase.getDefaultExpenseList()
    }
}