package com.sk.nytarticlesdashboard.flow.auth

import android.app.Application
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.models.local.UserDAO
import com.sk.nytarticlesdashboard.models.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(val context:Application,val userDAO: UserDAO):AuthRepositoryInterface {
    override suspend fun getUserById(email: String): UserModel {
       return userDAO.getUserByEmail(email)
    }

    override suspend fun registerUser(userModel: UserModel) {
        userDAO.insertUser(userModel = userModel)
    }

    override suspend fun saveUserId(id: Int) {
        AppPrefrencesHelper.saveLoggedInUserId(context,id)
    }
}