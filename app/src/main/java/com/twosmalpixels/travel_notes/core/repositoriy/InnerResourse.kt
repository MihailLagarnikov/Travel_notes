package com.twosmalpixels.travel_notes.core.repositoriy

import com.twosmalpixels.travel_notes.R

private val IMG_ADD_NEW_TRAVEL_ = "add_new_travel_img"
private val DEF_SKIN_TRAVEL_A_ = "ic_img_new_travel_default"
private val LOAD_ERROR_ = "load_error"
private val IC_IMG_NEW_TRAVEL_MOUNTY_ = "ic_img_new_travel_mounty"
private val IC_IMG_NEW_TRAVEL_MOUNTY_AND_VILAGE_ = "ic_img_new_travel_mounty_and_vilage"
private val IC_IMG_NEW_TRAVEL_SMAL_CITY_ = "ic_img_new_travel_smal_city"
private val IC_IMG_NEW_TRAVEL_MOUNTY_AND_LAKE_ = "ic_img_new_travel_mounty_and_lake"

enum class InnerImage(val img: String, val drawable: Int){
    IMG_ADD_NEW_TRAVEL(IMG_ADD_NEW_TRAVEL_, R.drawable.ic_add_travel),
    LOAD_ERROR(LOAD_ERROR_, R.drawable.load_error),
    DEF_SKIN_TRAVEL_A(DEF_SKIN_TRAVEL_A_, R.drawable.ic_img_new_travel_default),
    IC_IMG_NEW_TRAVEL_MOUNTY(IC_IMG_NEW_TRAVEL_MOUNTY_, R.drawable.ic_img_new_travel_mounty),
    IC_IMG_NEW_TRAVEL_MOUNTY_AND_VILAGE(IC_IMG_NEW_TRAVEL_MOUNTY_AND_VILAGE_, R.drawable.ic_img_new_travel_mounty_and_vilage),
    IC_IMG_NEW_TRAVEL_SMAL_CITY(IC_IMG_NEW_TRAVEL_SMAL_CITY_, R.drawable.ic_img_new_travel_smal_city),
    IC_IMG_NEW_TRAVEL_MOUNTY_AND_LAKE(IC_IMG_NEW_TRAVEL_MOUNTY_AND_LAKE_, R.drawable.ic_img_new_travel_mounty_and_lake)
}

fun isInnerImage(name: String): Boolean{
    for (inerimage in InnerImage.values()){
        if (inerimage.img.equals(name)){
            return true
        }
    }
    return false
}

fun getInnerDrawable (name: String): Int{
    for (inerimage in InnerImage.values()){
        if (inerimage.img.equals(name)){
            return inerimage.drawable
        }
    }
    return InnerImage.LOAD_ERROR.drawable
}

fun getInnerName (id: Int): String{
    for (inerimage in InnerImage.values()){
        if (inerimage.drawable == id){
            return inerimage.name
        }
    }
    return InnerImage.LOAD_ERROR.name
}