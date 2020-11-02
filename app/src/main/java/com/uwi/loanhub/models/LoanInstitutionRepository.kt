package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class LoanInstitutionRepository (private val inputLoanInstitutionDao: LoanInstitutionDao) {


    val allLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getLoanInstitution()

    fun getLoanInstitutionUserSpecific (inputSex:String,  inputCreditScore:Int, inputLoanAmount: Int): List<LoanInstitution> {
        return inputLoanInstitutionDao.getLoanInstitutionUserSpecific(inputSex, inputCreditScore,   inputLoanAmount)
    }
}