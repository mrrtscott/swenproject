package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class LoanRatingRepository (private val inputLoanRatingDao: LoanRatingDao, inputLoanRatingArrayList: ArrayList<String>) {

    var inputUsername =  inputLoanRatingArrayList.get(0)
    var loanID = inputLoanRatingArrayList.get(1).toInt()

    val allLoanRating:LiveData<List<LoanRating>> = inputLoanRatingDao.getLoanRating(inputUsername, loanID)


    suspend fun addNewLoanRating(inputLoanRating: LoanRating){
        inputLoanRatingDao.addLoanRating (inputLoanRating)
    }

}