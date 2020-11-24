package com.uwi.loanhub.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime
@Parcelize
@Entity(tableName = "Users", primaryKeys = ["username"], indices = arrayOf(Index(value = ["email"], unique = true)))
data class User(

    @NotNull var firstName: String,
    @NotNull var lastName: String,
    @NotNull var email: String,
    @NotNull var username: String,
    @NotNull var password: String,
    var sex: String,
    var dob: String,
    var salary: Double,
    var creditScore: Int,
    var city:String,
    var parish: String,
    var primaryBank: String,
    var loanType: String,
    var loanAmount: Double,
    var occupation: String,
    var dateCreated: String
) : Parcelable