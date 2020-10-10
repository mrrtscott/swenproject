package com.uwi.loanhub

data class Loans (
    val id: Int,
    val institution :Institution,
    val loanAmount: Int,
    val interestRate: Double,
    val termsRepay:String,
    val percentFinancing: Double,
    val creditScore: Int )