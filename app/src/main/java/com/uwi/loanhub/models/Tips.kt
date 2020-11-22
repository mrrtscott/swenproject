package com.uwi.loanhub.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tips")
data class Tips (
    @PrimaryKey (autoGenerate = true) val id:Int ,
    val tip: String
)
