package com.richve.Dinnerdecider.ui

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.preference.PreferenceManager
import java.security.Key

public var foodList = arrayListOf<String>("Sushi", "mcdonalds", "pizza", "Chinese","Sushi", "mcdonalds", "pizza", "Chinese","Sushi", "mcdonalds", "pizza", "Chinese")
public var defaultList = foodList.joinToString(",")
fun setDefaults(key: String?, value: String?, context: Context?) {
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = preferences.edit()

    if (value.isNullOrEmpty()){
        editor.remove(key)
    } else
        editor.putString(key, value)
    editor.commit()
}

fun getDefaults(key: String?, context: Context?): String? {
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    return preferences.getString(key, defaultList)
}


