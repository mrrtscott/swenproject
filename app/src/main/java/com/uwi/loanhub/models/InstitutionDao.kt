package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InstitutionDao {


    @Query("SELECT * FROM Institutions")
    fun getAllInstitutions(): LiveData<List<Institution>>

    @Query("SELECT * FROM Institutions WHERE name = :inputName ")
    fun getUSpecificInstitution (inputName: String):List<Institution>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewInstitution(inputInstitution:Institution)

    @Query("DELETE FROM Institutions")
    suspend fun deleteAllInstitutions()

}