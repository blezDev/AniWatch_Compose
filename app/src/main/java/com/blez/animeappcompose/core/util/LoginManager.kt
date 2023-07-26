package com.blez.animeappcompose.core.util

import android.content.Context
import com.blez.animeappcompose.core.Constants.GENDER
import com.blez.animeappcompose.core.Constants.PREFS_LOGIN_FILE

class LoginManager(context: Context) {
    private val sharedPrefs = context.getSharedPreferences(PREFS_LOGIN_FILE, Context.MODE_PRIVATE)

    fun savePersonGender(value: String) {
        val editor = sharedPrefs.edit()
        editor.putString(GENDER, value)
        editor.apply()
    }

    fun getPersonGender(): String? {
        return sharedPrefs.getString(GENDER, "MALE")
    }
}