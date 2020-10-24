package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM Users WHERE username = :inputUsername AND password = :inputPassword")
    fun getUsernamePassword (inputUsername: String, inputPassword: String): List<User> //Will be used to confirm user.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewUser(user: User)

}