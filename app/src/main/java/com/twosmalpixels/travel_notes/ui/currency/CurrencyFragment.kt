package com.twosmalpixels.travel_notes.ui.currency

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.new_travel.NewTravelsViewModel
import kotlinx.android.synthetic.main.currency_fragmnet.*

class CurrencyFragment: BaseFragment() {
    private lateinit var newTravelsViewModel: NewTravelsViewModel
    private lateinit var currencyViewModel: CurrencyViewModel

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(getString(R.string.currency_expence))
    }

    override fun getLayout(): Int {
        return  R.layout.currency_fragmnet
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newTravelsViewModel =
            ViewModelProviders.of(requireActivity()).get(NewTravelsViewModel::class.java)
        currencyViewModel =
            ViewModelProviders.of(requireActivity()).get(CurrencyViewModel::class.java)
        currencyViewModel.mainCurrencyList.observe(this, Observer {
            my_currency_view.setMainCurrencyList(it)
        })
        currencyViewModel.additionalCurrencyList.observe(this, Observer {
            my_currency_view.setAdditionalCurrencyList(it)
        })
        my_currency_view.isValidate.observe(this, Observer {
            button_currency_save.isEnabled = it
        })
        my_currency_view.callAllCurrencyDialog = {isMainCurrency ->
            currencyViewModel.isChooseMainCurrency = isMainCurrency
            findNavController().navigate(R.id.action_currencyFragment_to_allCurrencyDialog)
        }
        button_currency_save.setOnClickListener {
            newTravelsViewModel.currencyText.value = getCurrencyText()
            newTravelsViewModel.mainCurrencyCode = my_currency_view.getMainCurrencuCode()
            newTravelsViewModel.additionalCurrencyCode = my_currency_view.getAdditionalCurrencuCode()
            newTravelsViewModel.rates = my_currency_view.getRates()
            requireActivity().onBackPressed()
        }
        currencyViewModel.mainCurrencyListSelection.observe(this, Observer {
                my_currency_view.setSelection(true, it)
        })

        currencyViewModel.additionalCurrencyListSelection.observe(this, Observer {
            my_currency_view.setSelection(false, it)
        })
    }

    private fun getCurrencyText(): String{
        return if (my_currency_view.getAdditionalCurrencuCode() == null){
            my_currency_view.getMainCurrencuCode() ?: ""
        }else{
            my_currency_view.getMainCurrencuCode() + ", " + my_currency_view.getAdditionalCurrencuCode()
        }
    }
}