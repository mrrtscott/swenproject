package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel (application: Application): AndroidViewModel(application) {
    private val repository: UserRepository

    val allUsers: LiveData<List<User>>

    init {
        val UserDao = UserDatabase.getDatabase(application, viewModelScope).UserDao()
        repository = UserRepository(UserDao)

        allUsers = repository.allUsers

    }


    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.addNewUser(user)
    }
}