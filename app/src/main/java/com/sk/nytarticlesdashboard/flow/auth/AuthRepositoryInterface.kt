package com.sk.nytarticlesdashboard.flow.auth

import com.sk.nytarticlesdashboard.models.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryInterface {
    suspend fun getUserById(email:String): UserModel
    suspend fun registerUser(userModel: UserModel)
    suspend fun saveUserId(id: Int)
}