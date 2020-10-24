package com.uwi.loanhub.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Loans")
data class LoanModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var institution : String,
    var loanName: String,
    var loanAmount: Int,
    var interestRate: Double,
    var termsRepay:String,
    var percentFinancing: Double,
    var creditScore: Int,
    var description: String,
    var target:String,
    var status: String,
    var dateCreated: Date
)