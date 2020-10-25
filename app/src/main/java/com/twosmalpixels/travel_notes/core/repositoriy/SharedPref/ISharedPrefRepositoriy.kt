package com.twosmalpixels.travel_notes.core.repositoriy.SharedPref

import android.content.SharedPreferences

interface ISharedPrefRepositoriy {

    fun loadText(key: String, defValue: String): String
    fun saveText(key: String, value: String)
    fun loadBoolean(key: String, defValue: Boolean): Boolean
    fun saveBoolean(key: String, value: Boolean)
    fun init(pref: SharedPreferences)
}