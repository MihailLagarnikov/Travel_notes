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
import com.twosmalpixels.travel_notes.core.extension.equalsCurrency
import com.twosmalpixels.travel_notes.core.extension.getSelectedCurrencyData
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import kotlinx.android.synthetic.main.currency_view.view.*

class CurrencyView: ConstraintLayout, AdapterView.OnItemSelectedListener, TextWatcher {

    private val ONE = "1"
    private val EQUALS = "="

    val isValidate = MutableLiveData<Boolean>()
    var callAllCurrencyDialog: ((isMainCurrency: Boolean) -> Unit)? = null

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

    fun setSelection(isMainCurrency: Boolean, selection: Int){
        if (isMainCurrency){
            main_currency_spiner.setSelection(if (additional_currency_spiner.equalsCurrency(main_currency_spiner, selection))
                 0 else selection)
        }else{
            additional_currency_spiner.setSelection(if (main_currency_spiner.equalsCurrency(additional_currency_spiner, selection))
                0 else selection)
        }
    }

    fun getMainCurrencuCode(): String?{
        return main_currency_spiner.getSelectedCurrencyData().currencyIso
    }

    fun getAdditionalCurrencuCode(): String?{
        return if (additional_currency_spiner.getSelectedCurrencyData().currencyIso.equals("")){
             main_currency_spiner.getSelectedCurrencyData().currencyIso
        }else{
            additional_currency_spiner.getSelectedCurrencyData().currencyIso
        }
    }

    fun getRates(): Int{
        try {
            return rates_edit_text.text.toString().toInt()
        } catch (e: Exception) {
            return 1
        }
    }

    fun isValidate(): Boolean{
        return (main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition == 0)
                || (main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition != 0
                && rates_edit_text.text != null && !rates_edit_text.text.isEmpty()  && rates_edit_text.text.toString().isDigitsOnly()
                && !main_currency_spiner.equalsCurrency(additional_currency_spiner))
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        card_rates_currency.setVisibility(main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition != 0)
        setRatesText()
        hint_additional_rates.setVisibility(main_currency_spiner.selectedItemPosition != 0 && additional_currency_spiner.selectedItemPosition != 0)
        if (p1 == additional_currency_spiner){
            rates_edit_text.requestFocus()
        }
        isValidate.value = isValidate()
        if (p0?.id == R.id.main_currency_spiner && p2 == (main_currency_spiner?.adapter?.count?.minus(1))){
            callAllCurrencyDialog?.invoke(true)
        }
        if (p0?.id == R.id.additional_currency_spiner && p2 == (additional_currency_spiner?.adapter?.count?.minus(1))){
            callAllCurrencyDialog?.invoke(false)
        }
       isValidate.value = isValidate()
    }

    private fun setRatesText(){
        val startText = ONE + " " + main_currency_spiner.getSelectedCurrencyData().currencyIso + " " + EQUALS + " "
        rates_text_start.text = startText
        rates_text_end.text = additional_currency_spiner.getSelectedCurrencyData().currencyIso
    }

    override fun afterTextChanged(p0: Editable?) {
        isValidate.value = isValidate()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}