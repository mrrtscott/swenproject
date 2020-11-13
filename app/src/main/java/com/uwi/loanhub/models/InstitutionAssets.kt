package com.uwi.loanhub.models

import androidx.room.Entity

@Entity(tableName = "InstitutionAssets", primaryKeys = ["institutionName", "year"])
data class InstitutionAssets (
    val institutionName:String,
    val year:Int,
    val revenue: Double,
    val income: Double,
    val totalAssets: Double

)