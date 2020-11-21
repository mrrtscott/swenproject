package com.uwi.loanhub.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoanInstitution(
    var id: Int,
    var institution : String?,
    var loanName: String?,
    var loanAmount: Int?,
    var interestRate: Double?,
    var termsRepay:String?,
    var percentFinancing: Double?,
    var creditScore: Int?,
    var description: String?,
    var target:String?,
    var status: String?,
    var name:String?,
    var about:String?,
    var slogan:String?,
    var logo:Int?,
    var email:String?,
    var phone:String?,
    var website:String?,
    var openingHours:String?,
    var institutionStatus:String?
) : Parcelable