package com.uwi.loanhub.models

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository (private val inputUserDao: UserDao) {

    val allUsers: LiveData<List<User>> = inputUserDao.getAllUsers()

    suspend fun addNewUser(user: User){
         inputUserDao.addNewUser(user)

    }

     fun getUsernamePassword(inputUserName:String, inputPassword:String): List<User> {
         return inputUserDao.getUsernamePassword(inputUserName, inputPassword)

    }
}