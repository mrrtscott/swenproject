package com.uwi.loanhub.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Glossary")
data class Glossary (

    @PrimaryKey val word:String,
    val definition:String

)