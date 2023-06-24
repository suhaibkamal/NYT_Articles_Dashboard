package com.sk.nytarticlesdashboard.flow.home

import androidx.lifecycle.LiveData
import com.sk.movieshare.base.Resource
import com.sk.nytarticlesdashboard.models.model.ArticleCellModel
import com.sk.nytarticlesdashboard.models.model.ArticlesResponse
import com.sk.nytarticlesdashboard.models.model.UserModel
import kotlinx.coroutines.flow.Flow

interface HomeRepositoryInterface {
    suspend fun getArticleList(): Resource<ArticlesResponse>
    suspend fun insertArticle(article: ArticleCellModel)
    suspend fun insertArticle(articles: List<ArticleCellModel>)
    suspend fun getArticles(): List<ArticleCellModel>
    suspend fun getUserById(): UserModel
}