package com.twosmalpixels.travel_notes.ui.currency

import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.views.currency.CurrencyData

interface ICurrencyUseCase {

    fun getUsualyCurrencyList(countryCode: String, hint: Int): ArrayList<CurrencyData>

    fun createCurrensyListFromItem(travelsItem: TravelsItem, allCurrencyList: ArrayList<CurrencyData>): ArrayList<CurrencyData>
}