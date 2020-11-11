package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class LoanInstitutionRepository (private val inputLoanInstitutionDao: LoanInstitutionDao, inputArray:ArrayList<String>) {







    val allLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getLoanInstitution()
    val specificLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getSpecificLoan(inputArray.get(0).toInt())
    var loanInstitutionLikesRating:LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getLikedLoans(inputArray.get(1))

    fun getLoanInstitutionUserSpecific (inputSex:String,  inputCreditScore:Int, inputLoanAmount: Int): List<LoanInstitution> {
        return inputLoanInstitutionDao.getLoanInstitutionUserSpecific(inputSex, inputCreditScore,   inputLoanAmount)
    }
}