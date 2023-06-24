package com.sk.nytarticlesdashboard.models.remote

import com.sk.nytarticlesdashboard.models.model.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesAPI {

    @GET("svc/mostpopular/v2/viewed/30.json")
    suspend fun getMostPopularArticlesList(@Query("api-key") apiKey:String) : ArticlesResponse
}