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


        val institutionName = findViewById<TextView>(R.id.institutionNameLoanDetails)
        val institutionLogo = findViewById<ImageView>(R.id.institutionLogoLoanDetails)
        val loanName = findViewById<TextView>(R.id.loanNameLoanDetails)
        val loanEmail = findViewById<TextView>(R.id.institutionEmailLoanDetails)
        val loanPhone = findViewById<TextView>(R.id.institutionPhoneLoanDetails)
        val loanDetails = findViewById<TextView>(R.id.loanDescriptionLoanDetails)
        val loanBranches= findViewById<TextView>(R.id.loanBranchLoanDetails)

        institutionName.text = selectedLoan.institution.getInstitutionName()
        institutionLogo.setImageResource(selectedLoan.institution.getLogo())
        loanName.text = selectedLoan.loanName
        loanEmail.text = selectedLoan.institution.getEmail()
        loanPhone.text= selectedLoan.institution.getPhone()
        loanDetails.text = selectedLoan.Description

        var branchString: String = ""

        var i = 0

        while(i<selectedLoan.institution.getBranch().size)
        {
            branchString = branchString.plus((selectedLoan.institution.getBranch().get(i)).plus("\n"))
            println((selectedLoan.institution.getBranch().get(i)).plus("\n"))
            i++

        }

        println(branchString)


        loanBranches.text = branchString







    }



}