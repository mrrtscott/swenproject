package com.uwi.loanhub

import androidx.lifecycle.ViewModel

class LoanListViewModel : ViewModel(){

    val loan = mutableListOf<Loans>()

    init {
        val sagicor = Institution("Sagicor", "info@sagicor.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.sagicormg)
        loan += Loans(1, sagicor,"Ready Loan", 500000,7.5,"5 Years", 100.0, 90, "This is an example of a des.")


    }

}
