package com.sk.nytarticlesdashboard.models.model

import com.squareup.moshi.Json

data class MediaMetadata(
    @field:Json(name = "url") val url:String,
    @field:Json(name = "format") val format:String,
    @field:Json(name = "height") val height:Int,
    @field:Json(name = "width") val width:Int)
