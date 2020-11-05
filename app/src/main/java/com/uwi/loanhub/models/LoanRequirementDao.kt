package com.uwi.loanhub.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoanRequirementDao {

    @Query("SELECT * FROM LoanRequirement WHERE LoanRequirement.loanID = :inputLoanID AND LoanRequirement.institutionName = :inputInstitutionName")
    fun getAllRequirements(inputLoanID:Int, inputInstitutionName:String)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLoanRequirement(requirement: LoanRequirement)
}