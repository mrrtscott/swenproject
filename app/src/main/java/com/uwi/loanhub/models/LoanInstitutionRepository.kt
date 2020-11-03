package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class LoanInstitutionRepository (private val inputLoanInstitutionDao: LoanInstitutionDao, idLoan:String) {







    val allLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getLoanInstitution()
    val specificLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getSpecificLoan(idLoan)

    fun getLoanInstitutionUserSpecific (inputSex:String,  inputCreditScore:Int, inputLoanAmount: Int): List<LoanInstitution> {
        return inputLoanInstitutionDao.getLoanInstitutionUserSpecific(inputSex, inputCreditScore,   inputLoanAmount)
    }
}