package com.uwi.loanhub.models

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Repository class that handles data structures for the User class
 */
class UserRepository (private val inputUserDao: UserDao, inputArrayList: ArrayList<String>) {

    var inputUsername = inputArrayList[0]
    var inputPassword = inputArrayList[1]

    val allUsers: LiveData<List<User>> = inputUserDao.getAllUsers()
    val getUsernamePassword:LiveData<List<User>> = inputUserDao.getUsernamePassword(inputUsername, inputPassword)
    val getUser:LiveData<List<User>> = inputUserDao.getUser(inputUsername)


    suspend fun addNewUser(user: User){
         inputUserDao.addNewUser(user)

    }










}