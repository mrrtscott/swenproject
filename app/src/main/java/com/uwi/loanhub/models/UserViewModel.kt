package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserViewModel (application: Application): AndroidViewModel(application) {


    private var repository: UserRepository
    val allUsers: LiveData<List<User>>
    var userList: LiveData<List<User>>
    var singleUser:LiveData<List<User>>

    val UserDao:UserDao


    var receivedArrayList:ArrayList<String> = arrayListOf(" ", " ")


    init {
        UserDao = LoanHubDatabase.getDatabase(application, viewModelScope).UserDao()
        repository = UserRepository(UserDao, receivedArrayList)

        allUsers = repository.allUsers
        userList = repository.getUsernamePassword
        singleUser = repository.getUser



        
        
    }


     fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.addNewUser(user)
    }





    fun inputArrayList(inputArrayList: ArrayList<String>){

        receivedArrayList = inputArrayList
        repository = UserRepository(UserDao, receivedArrayList)
        userList = repository.getUsernamePassword
        singleUser = repository.getUser

    }









}