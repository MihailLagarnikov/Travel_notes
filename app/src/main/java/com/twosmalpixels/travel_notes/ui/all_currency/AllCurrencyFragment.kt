package com.twosmalpixels.travel_notes.ui.all_currency

import BaseFragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.currency.CurrencyViewModel
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import kotlinx.android.synthetic.main.all_currency_dialog.*

class AllCurrencyFragment(): BaseFragment(), TextWatcher {

    private lateinit var currencyViewModel: CurrencyViewModel

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam()
    }

    override fun getLayout(): Int {
        return R.layout.all_currency_dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyViewModel =
            ViewModelProviders.of(requireActivity()).get(CurrencyViewModel::class.java)
        recycler_all_currency.layoutManager = LinearLayoutManager(context)
        recycler_all_currency.adapter =
            AllCurrencyAdapter(
                currencyViewModel.getAllCurrency(currencyViewModel.getHint(currencyViewModel.isChooseMainCurrency))
            )
            { currencyData, position -> clickCurrency(currencyData, position) }
        currency_search_edit_text.addTextChangedListener(this)
    }

    private fun clickCurrency(currencyData: CurrencyData, position: Int){
        if (currencyViewModel.isChooseMainCurrency){
            currencyViewModel.addMainCurrency(currencyData)
            currencyViewModel.mainCurrencyListSelection.value = 1
        }else{
            currencyViewModel.addAdditionalCurrency(currencyData)
            currencyViewModel.additionalCurrencyListSelection.value = 1
        }
        requireActivity().onBackPressed()
    }

    override fun afterTextChanged(p0: Editable?) {
        (recycler_all_currency.adapter as AllCurrencyAdapter).sortList(p0.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}