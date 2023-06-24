package com.sk.movieshare.di


import com.sk.nytarticlesdashboard.flow.auth.AuthRepository
import com.sk.nytarticlesdashboard.flow.auth.AuthRepositoryInterface
import com.sk.nytarticlesdashboard.flow.home.HomeRepository
import com.sk.nytarticlesdashboard.flow.home.HomeRepositoryInterface
import com.sk.nytarticlesdashboard.flow.splash.SplashRepository
import com.sk.nytarticlesdashboard.flow.splash.SplashRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepository: HomeRepository
    ): HomeRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        homeRepository: AuthRepository
    ): AuthRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindSplashRepository(
        homeRepository: SplashRepository
    ): SplashRepositoryInterface

}