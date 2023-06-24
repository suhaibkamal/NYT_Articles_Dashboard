package com.sk.nytarticlesdashboard.base

import android.content.Context
import android.os.Build
import java.util.Locale

class LocalHelper {

    fun setLocal(language: String?, context: Context) {
        val locale = Locale(language)
        val resources = context.resources
        val displayMetrics = resources.displayMetrics
        val configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, displayMetrics)
    }
}