package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class LoanInstitutionRepository (private val inputLoanInstitutionDao: LoanInstitutionDao) {


    val allLoanInstitution: LiveData<List<LoanInstitution>> = inputLoanInstitutionDao.getLoanInstitution()
}