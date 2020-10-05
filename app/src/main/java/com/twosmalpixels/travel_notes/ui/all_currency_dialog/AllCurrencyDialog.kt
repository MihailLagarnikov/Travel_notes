package com.twosmalpixels.travel_notes.ui.all_currency_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.ui.currency.CurrencyViewModel
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import kotlinx.android.synthetic.main.all_currency_dialog.*

class AllCurrencyDialog(): DialogFragment() {

    private lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_currency_dialog, container, false)
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
    }

    private fun clickCurrency(currencyData: CurrencyData, position: Int){
        if (currencyViewModel.isChooseMainCurrency){
            currencyViewModel.addMainCurrency(currencyData)
            currencyViewModel.mainCurrencyListSelection.value = 1
        }else{
            currencyViewModel.addAdditionalCurrency(currencyData)
            currencyViewModel.additionalCurrencyListSelection.value = 1
        }

        dismiss()
    }
}