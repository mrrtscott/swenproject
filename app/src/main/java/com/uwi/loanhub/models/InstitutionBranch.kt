package com.uwi.loanhub.models

import androidx.room.Entity


@Entity(tableName =  "InstitutionBranch", primaryKeys = ["institutionPriKey", "branchPriKey"])
data class InstitutionBranch (
    var institutionPriKey: String,
    var branchPriKey: String
)