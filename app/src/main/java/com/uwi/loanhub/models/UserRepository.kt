package com.uwi.loanhub.models

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository (private val inputUserDao: UserDao) {

    val allUsers: LiveData<List<User>> = inputUserDao.getAllUsers()


    suspend fun addNewUser(user: User){
         inputUserDao.addNewUser(user)

    }

    fun  getUsernamePassword (inputUsername: String, inputPassword: String):List<User>{

        return inputUserDao.getUsernamePassword(inputUsername, inputPassword)

    }

    fun  getUser (inputUsername: String):List<User>{

        return inputUserDao.getUser(inputUsername)

    }







}