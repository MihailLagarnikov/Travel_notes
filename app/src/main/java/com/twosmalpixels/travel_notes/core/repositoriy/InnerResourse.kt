package com.twosmalpixels.travel_notes.core.repositoriy

import com.twosmalpixels.travel_notes.R

private val IMG_ADD_NEW_TRAVEL_ = "add_new_travel_img"
private val DEF_SKIN_TRAVEL_A_ = "ic_img_new_travel_default"
private val LOAD_ERROR_ = "load_error"

enum class InnerImage(val img: String, val drawable: Int){
    IMG_ADD_NEW_TRAVEL(IMG_ADD_NEW_TRAVEL_, R.drawable.ic_add_travel),
    LOAD_ERROR(LOAD_ERROR_, R.drawable.load_error),
    DEF_SKIN_TRAVEL_A(DEF_SKIN_TRAVEL_A_, R.drawable.ic_img_new_travel_default)
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