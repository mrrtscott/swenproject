package com.uwi.loanhub.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

/* Used to construct a database table that will hold the branches of the various banks */

@Entity (tableName = "BankBranch", primaryKeys = ["name", "bank"] )
data class Branch (
    var name: String,
    var bank: String,
    var street: String,
    var city: String,
    var parish: String,
    var closeTo: String,
    var latitude: Double,
    var longitude: Double,
    var manager: String,
    var status: String

)