package com.blez.animeappcompose.common

import android.content.Context
import com.blez.animeappcompose.common.utils.Constants

class HistoryManager(context: Context) {
    private val sharedPrefs = context.getSharedPreferences(Constants.HISTORY_PREFERENCE,Context.MODE_PRIVATE)

    fun savePreviousAnime(anime : String){
        val editor = sharedPrefs.edit()
        editor.putString(Constants.PREVIOUS_ANIME,anime)
        editor.apply()
    }

    fun getPreviousAnime() : String{
        return sharedPrefs.getString(Constants.PREVIOUS_ANIME,"Naruto").toString()
    }
}