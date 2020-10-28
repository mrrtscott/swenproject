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
    private val repository: UserRepository


    val allUsers: LiveData<List<User>>
    val userList = MutableLiveData<List<User>>()


    init {
        val UserDao = LoanHubDatabase.getDatabase(application, viewModelScope).UserDao()
        repository = UserRepository(UserDao)

        allUsers = repository.allUsers
        repository.allUsers

        
        
    }


     fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.addNewUser(user)
    }

      fun getUsernamePassword(inputUserName:String, inputPassword:String) = viewModelScope.launch(Dispatchers.IO) {
         val list = repository.getUsernamePassword(inputUserName, inputPassword)
         userList.postValue(list)
    }









}