package com.twosmalpixels.travel_notes.core.repositoriy.SharedPref

import android.content.SharedPreferences

interface ISharedPrefHelper {

    fun loadText(key: String, defValue: String): String
    fun saveText(key: String, value: String)
    fun loadBoolean(key: String, defValue: Boolean): Boolean
    fun saveBoolean(key: String, value: Boolean)
    fun loadLong(key: String, defValue: Long): Long
    fun saveLong(key: String, value: Long)
    fun loadInt(key: String, defValue: Int): Int
    fun saveInt(key: String, value: Int)
    fun loadDouble(key: String, defValue: Double): Double
    fun saveDouble(key: String, value: Double)
    fun init(pref: SharedPreferences)
}