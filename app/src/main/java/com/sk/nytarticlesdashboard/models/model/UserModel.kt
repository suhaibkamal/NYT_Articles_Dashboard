package com.sk.nytarticlesdashboard.models.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.Date

@Entity
data class UserModel(
     val dateOfBirth: String,
     val email:String,
     val fullName:String,
     val nationalNumber:String,
     val phone:String,
     val password:String,
     @PrimaryKey(autoGenerate = true) val id:Int? = null)
