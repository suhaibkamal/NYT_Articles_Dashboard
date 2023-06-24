package com.sk.nytarticlesdashboard.models.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sk.nytarticlesdashboard.models.model.ArticleCellModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleCellModel)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articleList: List<ArticleCellModel>)

    @Query("SELECT * FROM ArticleCellModel")
    fun getSavedArticles():List<ArticleCellModel>
}