package com.uwi.loanhub.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime
import java.util.*


@Entity(tableName = "Institutions")
data class InstitutionModel(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var name:String,
    var about:String,
    var slogan:String,
    var logo:Int,
    var email:String,
    var phone:String,
    var website:String,
    var openingHours:String,
    var institutionStatus:String,
    var dateCreated: OffsetDateTime
)