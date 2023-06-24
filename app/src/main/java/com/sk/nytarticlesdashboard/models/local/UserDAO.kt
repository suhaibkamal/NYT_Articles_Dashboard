package com.sk.nytarticlesdashboard.models.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sk.nytarticlesdashboard.models.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(userModel: UserModel)

    @Query("SELECT * FROM UserModel where email=:email")
    fun getUserByEmail(email:String): UserModel

    @Query("SELECT * FROM UserModel where id=:id")
    fun getUserById(id:Int):UserModel
}