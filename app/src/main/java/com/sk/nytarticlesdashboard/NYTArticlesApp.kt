package com.sk.nytarticlesdashboard

import android.app.Application
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.base.LocalHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
public class NYTArticlesApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        LocalHelper().setLocal(AppPrefrencesHelper().getPreferredLanguage(this), this)

    }
}