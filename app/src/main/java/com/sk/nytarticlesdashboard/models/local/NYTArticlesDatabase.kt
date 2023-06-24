package com.sk.nytarticlesdashboard.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sk.nytarticlesdashboard.models.model.ArticleCellModel
import com.sk.nytarticlesdashboard.models.model.UserModel

@Database(
    entities = [ArticleCellModel::class,UserModel::class],
    version = 1
)
abstract class NYTArticlesDatabase : RoomDatabase() {
    abstract val articlesDAO:ArticlesDAO;
    abstract val userDAO:UserDAO;
}