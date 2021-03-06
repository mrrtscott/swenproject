package com.uwi.loanhub.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime
import java.util.*

@Parcelize
@Entity(tableName = "Loans", indices = arrayOf(Index(value = ["loanName"], unique = true)))
data class Loan(
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
    var dateCreated: String
) : Parcelable