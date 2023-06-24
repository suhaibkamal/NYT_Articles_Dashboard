package com.sk.nytarticlesdashboard.base

import android.content.Context

public  object AppPrefrencesHelper {

    private val PRE_LANG= "Lan"
    private val USER_ID = "user_id"
    private val USER_SHARED_PREF = "user_file"

    public fun saveLoggedInUserId(context: Context, id:Int){
        val sharedPref = context.getSharedPreferences(USER_SHARED_PREF,Context.MODE_PRIVATE)
        sharedPref.edit().putInt(USER_ID,id).apply()
    }

    public fun getLoggedInUserId(context: Context):Int{
        val sharedPref = context.getSharedPreferences(USER_SHARED_PREF,Context.MODE_PRIVATE)
        return sharedPref.getInt(USER_ID,0)
    }

    public fun savePreferredLanguage(context: Context, lg:String){
        val sharedPref = context.getSharedPreferences(USER_SHARED_PREF,Context.MODE_PRIVATE)
        sharedPref.edit().putString(PRE_LANG,lg).apply()
    }

    public fun getPreferredLanguage(context: Context): String {
        val sharedPref = context.getSharedPreferences(USER_SHARED_PREF,Context.MODE_PRIVATE)
        return sharedPref.getString(PRE_LANG,"en")!!
    }
}