package com.twosmalpixels.travel_notes.core.repositoriy.SharedPref

import android.content.SharedPreferences

interface ISharedPrefRepositoriy {

    fun loadText(key: String, defValue: String): String
    fun saveText(key: String, value: String)
    fun init(pref: SharedPreferences)
}