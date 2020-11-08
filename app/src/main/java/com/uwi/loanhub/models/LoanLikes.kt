package com.uwi.loanhub.models

import androidx.room.Entity

@Entity(tableName = "LoanLikes", primaryKeys = ["loanID", "institution", "loanName", "username"])
data class LoanLikes (


    var username:String,
    var loanID:Int,
    var institution : String,
    var loanName: String,
    var value: Int



)