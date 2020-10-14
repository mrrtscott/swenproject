package com.uwi.loanhub


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoanDetails : AppCompatActivity() {

    lateinit var selectedLoan: Loans
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_details)
        getSelectedLoan()
        setSelectedLoan ()
    }


    private fun getSelectedLoan() {
        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("id")
        println("VALUEID"+parsedStringID)
        var xloan = AllLoan()
        selectedLoan = xloan.getFullLoanList().get(Integer.valueOf(parsedStringID))

    }


    private fun setSelectedLoan ()
    {


        val tv = findViewById<TextView>(R.id.institutionNameLoanDetails)
        val iv: ImageView = findViewById<ImageView>(R.id.institutionLogoLoanDetails)

        tv.text = selectedLoan.institution.getInstitutionName()
        iv.setImageResource(selectedLoan.institution.getLogo())

    }



}