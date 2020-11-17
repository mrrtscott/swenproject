package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GlossaryDao {

    @Query("SELECT * FROM Glossary WHERE Glossary.word = :inputWord")
    fun getDefinition(inputWord: String): LiveData<List<Glossary>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(glossary: Glossary)
}