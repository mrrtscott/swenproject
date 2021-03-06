package com.uwi.loanhub.models



import androidx.lifecycle.LiveData
class LoanLikesRepository(private val inputLoanLikesDao: LoanLikesDao,   inputArray:ArrayList<String>) {

    private var inputLoanID = inputArray[0].toInt()
    private var inputLoanName = inputArray[1]
    private var inputInstitution = inputArray[2]
    private var inputUsername = inputArray[3]





    val allLoanLikes:LiveData<List<LoanLikes>> = inputLoanLikesDao.getSpecificLoanLikes(inputLoanID, inputLoanName, inputInstitution, inputUsername)

    suspend fun addNewLoanLikes(inputLoanLikes: LoanLikes){
        inputLoanLikesDao.addLoanRequirement(inputLoanLikes)
    }
}