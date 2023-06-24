package com.sk.nytarticlesdashboard.base

import android.content.Context

public  class AppPrefrencesHelper {

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
}