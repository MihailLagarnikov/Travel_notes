package com.twosmalpixels.travel_notes.core.repositoriy

const val USERS_COLLECTION = "users"
const val TRAVELS_COLLECTION = "travels"

//for travels
private val TITLE_ = "title"
private val DATA_STRING_ = "data"
private val DATA_START_ = "data_start"
private val DATA_END_ = "data_end"
private val PERSON_ = "person"
private val IMAGE_ = "image"
private val MAIN_CURRENCY_ = "mainCurrency"
private val ADDITIONAL_CURRENCY_ = "additionalCurrency"
private val RATES_CURRENCY_ = "rates_currency"

enum class TravelsCollection(key: String){
    TITLE(TITLE_),
    DATA_STRING(DATA_STRING_),
    DATA_START(DATA_START_),
    DATA_END(DATA_END_),
    PERSON(PERSON_),
    IMAGE(IMAGE_),
    MAIN_CURRENCY(MAIN_CURRENCY_),
    ADDITIONAL_CURRENCY(ADDITIONAL_CURRENCY_),
    RATES_CURRENCY(RATES_CURRENCY_)
}