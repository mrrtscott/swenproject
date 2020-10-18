package com.uwi.loanhub.models

import java.util.*
import kotlin.collections.ArrayList

class UserModel (val id: Int,
                 val firstName: String,
                 val lastName: String,
                 val username: String,
                 val password: String,
                 val passwordConfirm: String,
                 val sex: String,
                 val dob: Date,
                 val salary: Double,
                 val city:String,
                 val parish: String,
                 val primaryBank: String,
                 val loanType: ArrayList<String>,
                 val loabAmount: Double,
                 val occupation: String )