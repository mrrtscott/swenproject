package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BranchDao {

    @Query("SELECT * FROM BankBranch WHERE BankBranch.bank = :inputBank")
    fun getBranchToBank(inputBank: String):LiveData<List<Branch>>

    @Query("SELECT * FROM BankBranch WHERE BankBranch.name = :inputBank AND (BankBranch.closeTo LIKE '%' || :inputClose || '%' OR BankBranch.parish = :inputParish)")
    fun getCloseBranch(inputBank:String, inputClose:String, inputParish:String):LiveData<List<Branch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBranch(inputBranch: Branch)


}
