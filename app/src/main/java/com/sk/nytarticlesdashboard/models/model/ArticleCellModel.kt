package com.sk.nytarticlesdashboard.models.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ArticleCellModel(
    @PrimaryKey val id:Long,
    val title:String,
    val imageUrl:String? =null,
    val date:String,
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$title",
            "${title.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
