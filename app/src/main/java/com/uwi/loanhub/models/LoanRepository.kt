package com.uwi.loanhub.models


import androidx.lifecycle.LiveData
class LoanRepository(private val inputLoanDao: LoanDao) {

    val allLoans: LiveData<List<Loan>> = inputLoanDao.getAllLoans()


    suspend fun addNewLoan(inputLoan: Loan){
        inputLoanDao.addNewLoan(inputLoan)

    }





}