package com.uwi.loanhub.models

import java.util.*
import kotlin.collections.ArrayList
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime

@Entity(tableName = "Users")
data class UserModel (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @NotNull var firstName: String,
    @NotNull var lastName: String,
    @NotNull var email: String,
    @NotNull var username: String,
    @NotNull var password: String,
    var sex: String,
    var dob: Date,
    var salary: Double,
    var city:String,
    var parish: String,
    var primaryBank: String,
    var loanType: String,
    var loanAmount: Double,
    var occupation: String,
    var dateCreated: OffsetDateTime
)