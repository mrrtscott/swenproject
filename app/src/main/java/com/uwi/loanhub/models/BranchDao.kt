package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BranchDao {

    @Query("SELECT * FROM Branch WHERE Branch.bank = :inputBank")
    fun getBranchToBank(inputBank: String):LiveData<List<Branch>>

    @Query("SELECT * FROM Branch WHERE Branch.name = :inputBank AND (Branch.closeTo LIKE '%' || :inputClose || '%' OR Branch.parish = :inputParish)")
    fun getCloseBranch(inputBank:String, inputClose:String, inputParish:String):LiveData<List<Branch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBranch(inputBranch: Branch)


}
