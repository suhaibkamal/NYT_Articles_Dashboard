package com.sk.nytarticlesdashboard.models.model

import com.squareup.moshi.Json

data class ArticlesResponse(
    @field:Json(name = "num_results") val num_results:String,
    @field:Json(name = "status") val status:String,
    @field:Json(name = "results") val results:List<ArticleModel>)
