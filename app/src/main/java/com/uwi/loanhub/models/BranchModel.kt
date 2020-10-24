package com.uwi.loanhub.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime


@Entity
data class BranchModel (
    @PrimaryKey var id: String,
    var name: String,
    var city: String,
    var parish: String,
    var closeTo: String,
    var status: String,
    var dateCreated: OffsetDateTime


)