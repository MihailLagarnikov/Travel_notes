package com.twosmalpixels.travel_notes.core.repositoriy

const val USERS_COLLECTION = "users"
const val TRAVELS_COLLECTION = "travels"
const val EXPENCE_COLLECTION = "expence"

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

//for expenceDtat
private val AMOUNT_ = "amount"
private val CURRENCY_ = "currency"
private val CATEGORY_ = "category"
private val COMMENT_ = "comment"
private val DATA_ = "data"
private val DATA_LONG_ = "dataLong"
private val IMAGEURL_ = "imageUrl"
private val LAT_ = "lat"
private val LON_ = "lon"

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

enum class ExpenceDataCollection(key: String){
    AMOUNT(AMOUNT_),
    CURRENCY(CURRENCY_),
    CATEGORY(CATEGORY_),
    COMMENT(COMMENT_),
    DATA(DATA_),
    DATA_LONG(DATA_LONG_),
    IMAGEURL(IMAGEURL_),
    LAT(LAT_),
    LON(LON_),

}