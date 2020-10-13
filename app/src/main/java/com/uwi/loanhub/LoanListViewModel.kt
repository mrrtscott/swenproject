package com.uwi.loanhub

import androidx.lifecycle.ViewModel

class LoanListViewModel : ViewModel(){

    var  loan = mutableListOf<Loans>()

    init {
        val sagicor = Institution("Sagicor", "info@sagicor.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.sagicormg)
        val jmmb = Institution("JMMB", "info@jmmb.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.jmmb)
        val ncb = Institution("NCB", "info@ncb.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.ncb)
        val firstglobal = Institution("First Global", "info@fgb.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.firstglobal)
        val jn = Institution("JN Bank", "info@jnbank.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.jn)
        val victoriamutal = Institution("Victoria Mutual", "info@victoriamutual.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.victoriamutual)
        val cibc = Institution("CIBC", "info@cibc.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.cibc)


        loan.add(Loans(1, sagicor,"Ready Loan", 100000,0.5,"5 Years", 100.0, 90, "This is an example of a des."))
        loan.add(Loans(2, jmmb,"Ready Loan", 200000,1.5,"4 Years", 90.0, 90, "This is an example of a des."))
        loan.add(Loans(3, ncb,"Ready Loan", 300000,2.5,"4 Years", 100.0, 70, "This is an example of a des."))
        loan.add(Loans(4, firstglobal,"Ready Loan", 400000,3.5,"7 Years", 80.0, 30, "This is an example of a des."))
        loan.add(Loans(5, jn,"Ready Loan", 500000,4.5,"9 Years", 70.0, 50, "This is an example of a des."))
        loan.add(Loans(6, victoriamutal,"Ready Loan", 600000,5.5,"6 Years", 100.0, 80, "This is an example of a des."))
        loan.add(Loans(7, cibc,"Ready Loan", 700000,6.5,"2 Years", 90.0, 100, "This is an example of a des."))





    }

     fun getLoanList(): MutableList<Loans>
    {
        return loan
    }





}
