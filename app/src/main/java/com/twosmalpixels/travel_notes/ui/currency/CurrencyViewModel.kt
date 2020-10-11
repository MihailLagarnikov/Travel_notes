package com.twosmalpixels.travel_notes.ui.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.ui.all_currency.IAllCurrencyUseCase
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import org.koin.java.standalone.KoinJavaComponent
import java.util.*
import kotlin.collections.ArrayList

class CurrencyViewModel: ViewModel() {
    private val iCurrencyViewModel: ICurrencyUseCase by KoinJavaComponent.inject(ICurrencyUseCase::class.java)
    private val iAllCurrencyUseCase: IAllCurrencyUseCase by KoinJavaComponent.inject(IAllCurrencyUseCase::class.java)

    val mainCurrencyList = MutableLiveData<ArrayList<CurrencyData>>()
    val additionalCurrencyList = MutableLiveData<ArrayList<CurrencyData>>()
    val mainCurrencyListSelection = MutableLiveData<Int>()
    val additionalCurrencyListSelection = MutableLiveData<Int>()
    var isChooseMainCurrency = false

    init {
        mainCurrencyList.value = iCurrencyViewModel.getUsualyCurrencyList(Locale.getDefault().country, R.string.main_currency)
        additionalCurrencyList.value = iCurrencyViewModel.getUsualyCurrencyList(Locale.getDefault().country, R.string.additional_currency)
    }

    fun addMainCurrency(currencyData: CurrencyData){
        mainCurrencyList.value?.set(1, currencyData)
    }

    fun addAdditionalCurrency(currencyData: CurrencyData){
        additionalCurrencyList.value?.set(1, currencyData)
    }

    fun getAllCurrency(hint: Int): ArrayList<CurrencyData>{
        return iAllCurrencyUseCase.getAllCurrency(hint)
    }

    fun getHint(isMain: Boolean): Int{
        return iAllCurrencyUseCase.getHint(isMain)
    }
}