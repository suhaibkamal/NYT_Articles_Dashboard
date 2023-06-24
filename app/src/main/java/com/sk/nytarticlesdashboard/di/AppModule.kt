package com.sk.movieshare.di

import android.app.Application
import androidx.room.Room

import com.sk.nytarticlesdashboard.models.local.ArticlesDAO
import com.sk.nytarticlesdashboard.models.local.NYTArticlesDatabase
import com.sk.nytarticlesdashboard.models.local.UserDAO
import com.sk.nytarticlesdashboard.models.remote.ArticlesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArticlesDatabase(app: Application): NYTArticlesDatabase {
        return Room.databaseBuilder(
            app,
            NYTArticlesDatabase::class.java,
            "nyt_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideArticlesApi():ArticlesAPI{
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ArticlesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesDao(db: NYTArticlesDatabase): ArticlesDAO {
        return db.articlesDAO
    }

    @Provides
    @Singleton
    fun provideUserDao(db: NYTArticlesDatabase): UserDAO {
        return db.userDAO
    }
}