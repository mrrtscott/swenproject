package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanRatingViewModel (application: Application): AndroidViewModel(application) {

    private var repository:LoanRatingRepository

    var allLoanRating:LiveData<List<LoanRating>>

    private var inputArray: ArrayList<String> =  arrayListOf(" ", "0")
    private var loanRatingDao: LoanRatingDao

    init{

        loanRatingDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanRatingDao()
        repository = LoanRatingRepository(loanRatingDao,inputArray)
        allLoanRating = repository.allLoanRating

    }


    fun insert(inputLoanRating: LoanRating) = viewModelScope.launch(Dispatchers.IO){
        repository.addNewLoanRating(inputLoanRating)
    }

    fun setArrayInput (inputArrayList: ArrayList<String>){
        inputArray = inputArrayList
        repository = LoanRatingRepository(loanRatingDao,inputArray)
        allLoanRating = repository.allLoanRating
    }

}