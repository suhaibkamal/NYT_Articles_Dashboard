package com.sk.nytarticlesdashboard.flow.splash

import kotlinx.coroutines.flow.Flow

interface SplashRepositoryInterface {

    suspend fun isUserLoggedIn():Boolean
}