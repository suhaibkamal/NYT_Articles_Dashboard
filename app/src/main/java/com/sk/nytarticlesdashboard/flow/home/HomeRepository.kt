package com.sk.nytarticlesdashboard.flow.home

import android.app.Application
import com.sk.movieshare.base.Resource
import com.sk.nytarticlesdashboard.R
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.models.local.ArticlesDAO
import com.sk.nytarticlesdashboard.models.local.UserDAO
import com.sk.nytarticlesdashboard.models.model.ArticleCellModel
import com.sk.nytarticlesdashboard.models.model.ArticlesResponse
import com.sk.nytarticlesdashboard.models.model.UserModel
import com.sk.nytarticlesdashboard.models.remote.ArticlesAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(val context: Application, val userDAO: UserDAO,val articlesDAO: ArticlesDAO,val articlesAPI: ArticlesAPI):HomeRepositoryInterface {
    override suspend fun getArticleList(): Resource<ArticlesResponse> {
        return try {
            Resource.Success(
                data = articlesAPI.getMostPopularArticlesList(
                    apiKey = context.getString(R.string.api_key)
                )
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: context.getString(R.string.server_error))
        }
    }

    override suspend fun insertArticle(article: ArticleCellModel) {
        return articlesDAO.insertArticle(article)
    }

    override suspend fun insertArticle(articles: List<ArticleCellModel>) {
        return articlesDAO.insertArticles(articles)
    }

    override suspend fun getArticles(): List<ArticleCellModel> {
        return articlesDAO.getSavedArticles()
    }

    override suspend fun getUserById(): UserModel {
        return userDAO.getUserById(AppPrefrencesHelper().getLoggedInUserId(context))
    }

    override suspend fun removeUserSession() {
        AppPrefrencesHelper().saveLoggedInUserId(context,0)
    }
}