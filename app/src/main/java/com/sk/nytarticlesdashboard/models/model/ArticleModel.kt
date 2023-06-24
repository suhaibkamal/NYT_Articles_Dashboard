package com.sk.nytarticlesdashboard.models.model

import com.squareup.moshi.Json

data class ArticleModel(
    @field:Json(name = "title") val title:String,
    @field:Json(name = "published_date") val date:String,
    @field:Json(name = "media") val media:List<MediaModel>,
    @field:Json(name = "id") val id:Long)
