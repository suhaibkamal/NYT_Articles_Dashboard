package com.sk.nytarticlesdashboard

import android.app.Application
import android.content.Context
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.base.LocalHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
public class NYTArticlesApp : Application()  {

    override fun onCreate() {
        super.onCreate()


    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(AppPrefrencesHelper.getPreferredLanguage(base)
            ?.let { LocalHelper.setLocale(context =base , it) })
    }
}