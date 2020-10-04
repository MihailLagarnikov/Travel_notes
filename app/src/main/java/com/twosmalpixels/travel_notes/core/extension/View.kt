package com.twosmalpixels.travel_notes.core.extension

import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView

fun View.setVisibility(visibility: Boolean, isGone: Boolean = true){
    this.visibility = if (visibility){
        View.VISIBLE
    }else if (isGone){
        View.GONE
    }else{
        View.INVISIBLE
    }
}

fun TextView.setNonEmptyText(text: String?){
    val invisibility = text == null || text.isEmpty()
    if (!invisibility){
        this.setText(text)
    }
    this.setVisibility(!invisibility)
}

fun Map<String, Any>.getFireString(key: String): String{
    if (!this.containsKey(key)) return ""
    return this.get(key).toString()
}

fun Map<String, Any>.getFireLong(key: String): Long{
    if (!this.containsKey(key)) return 0L
    return this.get(key) as Long
}

fun ImageView.setUri(uri: Uri){
    this.setImageBitmap(MediaStore.Images.Media.getBitmap(this.context.contentResolver, uri))
}