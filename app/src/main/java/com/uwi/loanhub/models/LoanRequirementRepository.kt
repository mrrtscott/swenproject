package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class LoanRequirementRepository(private val inputLoanRequirementDao: LoanRequirementDao, inputLoanRequirement:ArrayList<String>) {

    val inputInstitutionName = inputLoanRequirement.get(0)
    val allLoanRequirement: LiveData<List<LoanRequirement>> = inputLoanRequirementDao.getAllRequirements(inputInstitutionName)


    suspend fun addNewLoanRequirement(inputLoanRequirement: LoanRequirement){
        inputLoanRequirementDao.addLoanRequirement(inputLoanRequirement)
    }


}