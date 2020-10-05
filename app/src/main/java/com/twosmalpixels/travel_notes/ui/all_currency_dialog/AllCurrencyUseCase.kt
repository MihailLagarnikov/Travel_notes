package com.twosmalpixels.travel_notes.ui.all_currency_dialog

import android.annotation.SuppressLint
import android.icu.util.Currency
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import java.util.*
import kotlin.collections.ArrayList

class AllCurrencyUseCase: IAllCurrencyUseCase {

    @SuppressLint("NewApi")
    override fun getAllCurrency(hint: Int): ArrayList<CurrencyData> {
        val sourseList = Currency.getAvailableCurrencies()
        val rezultList = ArrayList<CurrencyData>()
        for (curr in sourseList){
            val text = curr.currencyCode + " (" + curr.getDisplayName(Locale.getDefault()) + ")"
            rezultList.add(CurrencyData(hint, text, curr.currencyCode, Locale.getDefault().country))
        }
        return rezultList
    }

    override fun getHint(isMainCurrency: Boolean): Int {
        return if (isMainCurrency){
            R.string.main_currency
        }else{
            R.string.additional_currency
        }
    }
}