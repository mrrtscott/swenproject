package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanLikesViewModel(application: Application): AndroidViewModel(application) {

    private var repository:LoanLikesRepository

    var allLoansLikes: LiveData<List<LoanLikes>>
    private var inputArray: ArrayList<String> =  arrayListOf("0", "h", "b", "c", "d")
    val LoanLikesDao:LoanLikesDao

    init{

        LoanLikesDao= LoanHubDatabase.getDatabase(application, viewModelScope).LoanLikesDao()
        repository = LoanLikesRepository(LoanLikesDao, inputArray)
        allLoansLikes = repository.allLoanLikes

    }

    fun insert(inputLoanLikes: LoanLikes) = viewModelScope.launch(Dispatchers.IO){
        repository.addNewLoanLikes(inputLoanLikes)
    }




    fun setArrayInput (inputArrayList: ArrayList<String>){

        inputArray = inputArrayList
        repository = LoanLikesRepository(LoanLikesDao, inputArray)
        allLoansLikes = repository.allLoanLikes


    }
}