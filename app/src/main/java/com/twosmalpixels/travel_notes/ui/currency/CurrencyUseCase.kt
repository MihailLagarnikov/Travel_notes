package com.twosmalpixels.travel_notes.ui.currency

import com.twosmalpixels.travel_notes.views.currency.CurrencyData

class CurrencyUseCase: ICurrencyUseCase {


    override fun getUsualyCurrencyList(countryCode: String, hint: Int): ArrayList<CurrencyData> {
        val list = arrayListOf<CurrencyData>(
            CurrencyData(hint, "", "", "in"),
            CurrencyData(hint, "", UsualyCurrency.RUB.name, "ru", UsualyCurrency.RUB.textDefault),
            CurrencyData(hint, "", UsualyCurrency.USD.name, "us", UsualyCurrency.USD.textDefault),
            CurrencyData(hint, "", UsualyCurrency.EUR.name, "eu", UsualyCurrency.EUR.textDefault),
            CurrencyData(hint, "", UsualyCurrency.TRY.name, "tr", UsualyCurrency.TRY.textDefault),
            CurrencyData(hint, "", UsualyCurrency.CNY.name, "cn", UsualyCurrency.CNY.textDefault),
            CurrencyData(hint, "", UsualyCurrency.THB.name, "th", UsualyCurrency.THB.textDefault),
            CurrencyData(hint, "", UsualyCurrency.KRW.name, "kr", UsualyCurrency.KRW.textDefault),
            CurrencyData(hint, "", UsualyCurrency.JPY.name, "jp", UsualyCurrency.JPY.textDefault),
            CurrencyData(hint, "", UsualyCurrency.INR.name, "in", UsualyCurrency.INR.textDefault),
            CurrencyData(hint, "", "", "in")
        )
        list.sortBy { it.countriCode.equals(countryCode) }
        return list
    }
}