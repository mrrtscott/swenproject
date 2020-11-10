package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface  LoanRatingDao {

    @Query("SELECT * FROM LoanRating WHERE LoanRating.username = :inputUsername AND LoanRating.loanID = :inputLoanID")
    fun getLoanRating (inputUsername:String, inputLoanID:Int): LiveData<List<LoanRating>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLoanRating(loanRating: LoanRating)
}