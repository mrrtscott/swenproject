package com.uwi.loanhub


import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

/**
 * This is an old class which managed the loan details
 */
class LoanDetails : AppCompatActivity() {

    lateinit var selectedLoan: Loans //Used to store the selected loan based on ID


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_details)
        getSelectedLoan()
        setSelectedLoan ()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun currencyFormatter(num: String): String? {
        val m = num.toDouble()
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(m)
    }


    private fun getSelectedLoan() {
        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("id")
        var xloan = AllLoan()
        selectedLoan = xloan.getFullLoanList().get(Integer.valueOf(parsedStringID))

    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun setSelectedLoan ()
    {

        val institutionName = findViewById<TextView>(R.id.institutionNameLoanDetails)
        val institutionLogo = findViewById<ImageView>(R.id.institutionLogoLoanDetails)
        val loanName = findViewById<TextView>(R.id.loanNameLoanDetails)
        val loanEmail = findViewById<TextView>(R.id.institutionEmailLoanDetails)
        val loanPhone = findViewById<TextView>(R.id.institutionPhoneLoanDetails)
        val loanDetails = findViewById<TextView>(R.id.loanDescriptionLoanDetails)
        val loanBranches= findViewById<TextView>(R.id.loanBranchLoanDetails)
        val loanAmount = findViewById<TextView>(R.id.loanAmountLoanDetails)
        val loanInterestRate = findViewById<TextView>(R.id.loanInterestRateLoanDetails)
        val loanRepay = findViewById<TextView>(R.id.loanTermsRepayLoanDetails)
        val loanPerCentFinance= findViewById<TextView>(R.id.loanPerCentFinancingLoanDetails)
        val loanMinCreditScore = findViewById<TextView>(R.id.loanMinCreditScoreLoanDetails)




        institutionName.text = selectedLoan.institution.getInstitutionName()
        institutionLogo.setImageResource(selectedLoan.institution.getLogo())
        loanName.text = selectedLoan.loanName
        loanEmail.text = selectedLoan.institution.getEmail()
        loanPhone.text= selectedLoan.institution.getPhone()
        loanDetails.text = selectedLoan.Description
        loanAmount.text = "Amount: $"+currencyFormatter(selectedLoan.loanAmount.toString())
        loanInterestRate.text = "Interest Rate: "+ selectedLoan.interestRate.toString()
        loanRepay.text = "Repay Terms: "+selectedLoan.termsRepay
        loanPerCentFinance.text = "Financing: "+selectedLoan.percentFinancing.toString()+"%"
        loanMinCreditScore.text = "Minimum Credit Score: "+selectedLoan.creditScore.toString()


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