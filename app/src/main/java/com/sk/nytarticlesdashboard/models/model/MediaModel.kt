package com.sk.nytarticlesdashboard.models.model

import com.squareup.moshi.Json

data class MediaModel(
    @field:Json(name = "type") val type:String,
    @field:Json(name = "media-metadata") val metadata:List<MediaMetadata>
)
