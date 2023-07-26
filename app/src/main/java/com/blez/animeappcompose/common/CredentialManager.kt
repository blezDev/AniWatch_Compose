package com.blez.animeappcompose.common

import android.content.Context
import com.blez.animeappcompose.common.utils.Constants.LOGIN_NAME
import com.blez.animeappcompose.common.utils.Constants.LOGIN_PREFERENCE

class CredentialManager(context : Context) {
    val sharedPrefs = context.getSharedPreferences(LOGIN_PREFERENCE,Context.MODE_PRIVATE)

    fun saveName(name : String){
        val editor = sharedPrefs.edit()
        editor.putString(LOGIN_NAME,name)
        editor.apply()
    }

    fun getName() : String {
        val name = sharedPrefs.getString(LOGIN_NAME,"User")
        return name.toString()
    }


}