package com.uwi.loanhub.models

import androidx.room.Entity


@Entity(tableName = "LoanRequirement", primaryKeys = ["loanID", "institutionName"])
data class LoanRequirement (

    var loanID:Int,
    var institutionName: String,
    var identification: String,
    var employment:String,
    var character:String,
    var vehicle:String




)