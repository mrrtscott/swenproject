package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TipsDao {

    @Query("SELECT * FROM Tips")
    fun getTips():LiveData<List<Tips>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTip(inputTips: Tips)
}