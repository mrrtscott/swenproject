package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class LoanRequirementRepository(private val inputLoanRequirementDao: LoanRequirementDao, inputLoanRequirement:ArrayList<String>) {
    val inputLoanID = inputLoanRequirement.get(0).toInt()
    val inputInstitutionName = inputLoanRequirement.get(1)
    val allLoanRequirement: LiveData<List<LoanRequirement>> = inputLoanRequirementDao.getAllRequirements(inputLoanID,inputInstitutionName)


}