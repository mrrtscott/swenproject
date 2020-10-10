package com.uwi.loanhub

object InstConstants {

    fun getLoans():ArrayList<Loans>{
        val loanList = ArrayList<Loans>()

        // List of Institutions


        val sagicor = Institution("Sagicor", "info@sagicor.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.sagicormg)

        //

        val loan1 = Loans(1, sagicor, 500000,7.5,"5 Years", 100.0, 90, "This is an example of a des.")
        loanList.add(loan1)
        return loanList
    }


}