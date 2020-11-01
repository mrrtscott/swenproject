package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class LoanInstitutionRepository (private val inputLoanInstitutionDao: LoanInstitutionDao) {


    val allLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getLoanInstitution()

    fun getLoanInstitutionUserSpecific (inputSex:String, inputSalary:Double, inputCreditScore:Int, inputOccupation:String, inputPrimaryBank:String, inputLoanType:String, inputLoanAmount: Int, inputTarget:String):List<LoanInstitution>  {
        return inputLoanInstitutionDao.getLoanInstitutionUserSpecific(inputSex, inputSalary, inputCreditScore, inputOccupation, inputPrimaryBank, inputLoanType, inputLoanAmount, inputTarget)
    }
}