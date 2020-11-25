package com.uwi.loanhub

import androidx.lifecycle.ViewModel


/**
 * An old set up of loans list
 */
class LoanListViewModel : ViewModel(){

    var  loan = mutableListOf<Loans>()

    init {
        val sagicor = Institution("Sagicor", "info@sagicor.com", "876-990-0909", arrayOf("Spanish Town","Mandeville", "May Pen"),R.drawable.sagicormg)
        val jmmb = Institution("JMMB", "info@jmmb.com", "876-990-0909", arrayOf("Negril", "Mandeville", "May Pen"),R.drawable.jmmb)
        val ncb = Institution("NCB", "info@ncb.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.ncb)
        val firstglobal = Institution("First Global", "info@fgb.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.firstglobal)
        val jn = Institution("JN Bank", "info@jnbank.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.jn)
        val victoriamutal = Institution("Victoria Mutual", "info@victoriamutual.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.victoriamutual)
        val cibc = Institution("CIBC", "info@cibc.com", "876-990-0909", arrayOf("Mandeville", "May Pen"),R.drawable.cibc)
        val scotiabank = Institution("Scotiabank", "info@scotiabank.com", "876-997-7909", arrayOf("Mandeville", "May Pen"),R.drawable.scotiabank)


        loan.add(Loans(0, sagicor,"Sagicor Loan", 100000,0.5,"5 Years", 100.0, 90, "Access flexible financing on used vehicles at competitive interest rates with Sagicor Bank and upgrade your ride today."))
        loan.add(Loans(1, jmmb,"JMMB Loan", 200000,1.5,"4 Years", 90.0, 90, "You have places to be and you have things to do. A car can get you there, but not just any car. You need something reliable; or even something cool. The vehicle you choose is up to you. Just know that, with some discipline and a smart saving strategy, that car is as good as yours."))
        loan.add(Loans(2, ncb,"NCB Loan", 300000,2.5,"4 Years", 100.0, 70, "Our FAST WHEELS promotion is our best rate yet. It’s quick, easy and you can get an approval now!"))
        loan.add(Loans(3, firstglobal,"FGB Loan", 400000,3.5,"7 Years", 80.0, 30, "Need a new car? First Global Bank offers a comprehensive Motor Vehicle package that will get you driving your dream car in no time!"))
        loan.add(Loans(4, jn,"JN Loan", 500000,4.5,"9 Years", 70.0, 50, "A flexible, low interest loan that provides you with great value and service to make owning a motor vehicle easier."))
        loan.add(Loans(5, victoriamutal,"VM Loan", 600000,5.5,"6 Years", 100.0, 80, "Our new Drive Auto Loan product provides funding so you can purchase a new or pre-owned motor vehicle for personal use."))
        loan.add(Loans(6, cibc,"CIBC Loan", 700000,6.5,"2 Years", 90.0, 100, "With up to 8 years to pay off the loan and the possibility of no down payment, this loan makes it easy to purchase a new or used vehicle."))
        loan.add(Loans(7, scotiabank,"Scotiabank Loan", 900000,7.5,"5 Years", 90.0, 100, "Picking your colour will be the toughest part. We will have you moving in the right direction: a comprehensive suite of products that will make purchasing that new or used car easier than you think!"))

    }

     fun getLoanList(): MutableList<Loans>
    {
        return loan
    }





}
