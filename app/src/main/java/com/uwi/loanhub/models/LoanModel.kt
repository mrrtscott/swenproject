package com.uwi.loanhub.models


class LoanModel(val id: Int, val institution : String, val loanName: String, val loanAmount: Int, val interestRate: Double, val termsRepay:String, val percentFinancing: Double, val creditScore: Int, val description: String, val target:ArrayList<String>, val status: String )