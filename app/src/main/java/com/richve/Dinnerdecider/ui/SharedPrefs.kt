package com.richve.Dinnerdecider.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

var foodList = arrayListOf(
    "Chinese",
    "McDonald's",
    "Pizza",
    "Sushi",
    "Burgers",
    "Salad",
    "Portuguese",
    "Fish"
)
var defaultList = foodList.joinToString(",")
fun setDefaults(key: String?, value: String?, context: Context?) {
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = preferences.edit()

    if (value.isNullOrEmpty()){
        editor.remove(key)
    } else
        editor.putString(key, value)
    editor.apply()
}

    fun getDefaults(key: String?, context: Context?): String? {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, defaultList)
    }



