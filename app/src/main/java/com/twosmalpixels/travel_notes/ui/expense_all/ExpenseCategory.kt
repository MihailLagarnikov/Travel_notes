package com.twosmalpixels.travel_notes.ui.expense_all

import com.twosmalpixels.travel_notes.R


enum class ExpenceCategory(val number: Int, val logo: Int,val logoBlack: Int, val logoWhite: Int, val text: Int){
    TRANSPORT(0, R.drawable.ic_icon_transport, R.drawable.ic_icon_transport_black, R.drawable.ic_icon_transport_white, R.string.transport),
    HOME(1, R.drawable.ic_icon_home, R.drawable.ic_icon_home_black, R.drawable.ic_icon_home_white, R.string.booking),
    CAFE(2, R.drawable.ic_icon_cafe, R.drawable.ic_icon_cafe_black, R.drawable.ic_icon_cafe_white,  R.string.cafe_and_restorans),
    SUPERMARKET(3,R.drawable.ic_icon_supermarket, R.drawable.ic_icon_supermarket_black, R.drawable.ic_icon_supermarket_white, R.string.supermarket),
    TOUR(4, R.drawable.ic_icon_tour, R.drawable.ic_icon_tour_black, R.drawable.ic_icon_tour_white,  R.string.tour),
    ENTETAIMENT(5, R.drawable.ic_icon_entertainment, R.drawable.ic_icon_entertainment_black, R.drawable.ic_icon_entertainment_white, R.string.entertainment),
    GIFT(6, R.drawable.ic_icon_gifts, R.drawable.ic_icon_gifts_black, R.drawable.ic_icon_gifts_white,  R.string.gifts),
    SHOPING(7, R.drawable.ic_icon_shopping, R.drawable.ic_icon_shopping_black, R.drawable.ic_icon_shopping_white, R.string.shopping),
    INSURANCE(8,R.drawable.ic_icon_safety, R.drawable.ic_icon_safety_black, R.drawable.ic_icon_safety_white, R.string.safety),
    VISA(9, R.drawable.ic_icon_visa, R.drawable.ic_icon_visa_black, R.drawable.ic_icon_visa_white, R.string.visa),
    PTHER(10, R.drawable.ic_icon_others, R.drawable.ic_icon_others_black, R.drawable.ic_icon_others_white, R.string.others)
}

fun getDrawableForNumber(number: Int): Int{
    for (categoria in ExpenceCategory.values()){
        if (categoria.number == number){
            return categoria.logo
        }
    }
    return ExpenceCategory.TOUR.logo
}

fun getTextForNumber(number: Int): Int{
    for (categoria in ExpenceCategory.values()){
        if (categoria.number == number){
            return categoria.text
        }
    }
    return ExpenceCategory.TOUR.logo
}