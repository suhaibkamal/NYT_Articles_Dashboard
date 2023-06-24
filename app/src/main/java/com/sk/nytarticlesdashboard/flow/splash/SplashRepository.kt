package com.sk.nytarticlesdashboard.flow.splash

import android.app.Application
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashRepository @Inject constructor(val context: Application): SplashRepositoryInterface{

    override suspend fun isUserLoggedIn(): Boolean {
        return AppPrefrencesHelper.getLoggedInUserId(context = context)>0
    }
}