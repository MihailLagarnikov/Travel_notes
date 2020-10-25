package com.twosmalpixels.travel_notes.core.repositoriy.SharedPref

import android.content.SharedPreferences


class SharedPrefHelper: ISharedPrefHelper {
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

    override fun loadBoolean(key: String, defValue: Boolean): Boolean {
        return pref.getBoolean(key, defValue) ?: defValue
    }

    override fun saveBoolean(key: String, value: Boolean) {
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putBoolean(key, value)
        ed.apply()
    }

    override fun loadLong(key: String, defValue: Long): Long {
        return pref.getLong(key, defValue) ?: defValue
    }

    override fun saveLong(key: String, value: Long) {
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putLong(key, value)
        ed.apply()
    }

    override fun loadInt(key: String, defValue: Int): Int {
        return pref.getInt(key, defValue) ?: defValue
    }

    override fun saveInt(key: String, value: Int) {
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putInt(key, value)
        ed.apply()
    }

    override fun loadDouble(key: String, defValue: Double): Double {
        return pref.getFloat(key, defValue.toFloat()).toDouble() ?: defValue
    }

    override fun saveDouble(key: String, value: Double) {
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putFloat(key, value.toFloat())
        ed.apply()
    }
}