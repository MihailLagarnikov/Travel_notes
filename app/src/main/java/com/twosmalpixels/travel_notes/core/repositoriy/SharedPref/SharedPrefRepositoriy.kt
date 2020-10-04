package com.twosmalpixels.travel_notes.core.repositoriy.SharedPref

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPrefRepositoriy: ISharedPrefRepositoriy {
    private lateinit var pref: SharedPreferences

    override fun init(pref: SharedPreferences){
        this.pref = pref
    }

    override fun saveText(key: String, value: String){
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putString(key, value)
        ed.apply()
    }

    override fun loadText(key: String, defValue: String): String{
        return pref.getString(key, defValue) ?: defValue
    }
}