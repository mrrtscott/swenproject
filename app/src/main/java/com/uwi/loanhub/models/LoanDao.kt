package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LoanDao {

    @Query("SELECT * FROM Loans")
    fun getAllLoans():LiveData<List<Loan>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewLoan(inputLoan:Loan)

    @Query("DELETE FROM Loans")
    suspend fun deleteAllLoans()




}