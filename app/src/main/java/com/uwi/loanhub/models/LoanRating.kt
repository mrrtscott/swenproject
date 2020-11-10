package com.uwi.loanhub.models

import androidx.room.Entity
import org.jetbrains.annotations.NotNull


@Entity (tableName = "LoanRating", primaryKeys = ["loanID", "username"])
data class LoanRating(

    var loanID:Int,
    var username: String,
    @NotNull var rating: Int



)