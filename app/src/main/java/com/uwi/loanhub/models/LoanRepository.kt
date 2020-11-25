package com.uwi.loanhub.models


import androidx.lifecycle.LiveData

/**
 * The repository class which manages the data for each loan
 */
class LoanRepository(private val inputLoanDao: LoanDao) {

    val allLoans: LiveData<List<Loan>> = inputLoanDao.getAllLoans()

    /**
     * A method which adds a new loan to the database
     * @param inputLoan A Loan
     */
    suspend fun addNewLoan(inputLoan: Loan){
        inputLoanDao.addNewLoan(inputLoan)

    }





}