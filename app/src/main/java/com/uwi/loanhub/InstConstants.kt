package com.uwi.loanhub

object InstConstants {

    fun getLoans():ArrayList<Loans>{
        val loanList = ArrayList<Loans>()

        // List of Institutions


        val sagicor = Institution("Sagicor", "info@sagicor.com", "876-990-0909", arrayOf("Mandeville"),R.drawable.sagicormg)

        //

        val loan1 = Loans(1, sagicor, 500000,7.5,"5 Years", 100.0, 90)
        loanList.add(loan1)
        return loanList
    }


}