package com.uwi.loanhub.models

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface InstitutionAssetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewAssets(inputAsset:InstitutionAssets)
}