package com.twosmalpixels.travel_notes.ui.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import org.koin.java.standalone.KoinJavaComponent
import java.util.*
import kotlin.collections.ArrayList

class CurrencyViewModel: ViewModel() {
    private val iCurrencyViewModel: ICurrencyUseCase by KoinJavaComponent.inject(ICurrencyUseCase::class.java)

    val mainCurrencyList = MutableLiveData<ArrayList<CurrencyData>>()
    val additionalCurrencyList = MutableLiveData<ArrayList<CurrencyData>>()

    init {
        mainCurrencyList.value = iCurrencyViewModel.getUsualyCurrencyList(Locale.getDefault().country, R.string.main_currency)
        additionalCurrencyList.value = iCurrencyViewModel.getUsualyCurrencyList(Locale.getDefault().country, R.string.additional_currency)
    }
}