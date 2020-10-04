package com.twosmalpixels.travel_notes.views.currency

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import kotlinx.android.synthetic.main.currency_view.view.*

class CurrencyView: ConstraintLayout, AdapterView.OnItemSelectedListener, TextWatcher {

    val isValidate = MutableLiveData<Boolean>()

    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.currency_view, this)
        rates_edit_text.addTextChangedListener(this)
        isValidate.value = isValidate()
    }

    fun setMainCurrencyList( currencyDataList: ArrayList<CurrencyData>){
        main_currency_spiner.adapter = CurrencySpinerAdapter(context, R.layout.currency_spiner_item, currencyDataList)
        main_currency_spiner.setPromptId(R.string.choose_skin_b)
        main_currency_spiner.setSelection(1)
        main_currency_spiner.onItemSelectedListener = this
    }

    fun setAdditionalCurrencyList( currencyDataList: ArrayList<CurrencyData>){
        val myAdapter = CurrencySpinerAdapter(context, R.layout.currency_spiner_item,currencyDataList)
        additional_currency_spiner.adapter = myAdapter
        additional_currency_spiner.onItemSelectedListener = this
    }

    fun getMainCurrencuCode(): String?{
        return (main_currency_spiner.selectedItem as CurrencyData).currencyIso
    }

    fun getAdditionalCurrencuCode(): String?{
        return (additional_currency_spiner.selectedItem as CurrencyData).currencyIso
    }

    fun getRates(): Int{
        try {
            return rates_edit_text.text.toString().toInt()
        } catch (e: Exception) {
            return 0
        }
    }

    fun isValidate(): Boolean{
        return (main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition == 0)
                || (main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition != 0
                && rates_edit_text.text != null && !rates_edit_text.text.isEmpty()  && rates_edit_text.text.toString().isDigitsOnly())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        card_rates_currency.setVisibility(main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition != 0)
        hint_additional_rates.setVisibility(main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition != 0)
        if (p1 == additional_currency_spiner){
            rates_edit_text.requestFocus()
        }
        isValidate.value = isValidate()
    }

    override fun afterTextChanged(p0: Editable?) {
        isValidate.value = isValidate()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}