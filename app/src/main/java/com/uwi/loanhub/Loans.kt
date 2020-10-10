package com.uwi.loanhub

import org.w3c.dom.Text

data class Loans (
    val id: Int,
    val institution :Institution,
    val loanName: String,
    val loanAmount: Int,
    val interestRate: Double,
    val termsRepay:String,
    val percentFinancing: Double,
    val creditScore: Int,
    val Description: String)