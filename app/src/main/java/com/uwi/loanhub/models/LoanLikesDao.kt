package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LoanLikesDao {

    @Query("SELECT * FROM LoanLikes")
    fun getLoanLikes (): LiveData<List<LoanLikes>>

    @Query("SELECT * FROM LoanLikes WHERE LoanLikes.loanID = :inputLoanID AND LoanLikes.loanName = :inputLoanName AND LoanLikes.institution = :inputInstitution AND LoanLikes.username = :inputUsername ")
    fun getSpecificLoanLikes (inputLoanID:Int, inputLoanName:String, inputInstitution:String,inputUsername:String):LiveData<List<LoanLikes>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLoanRequirement(loanLikes: LoanLikes)
}