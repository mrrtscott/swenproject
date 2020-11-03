package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.LoanInstitutionViewModel

class LoanInDetail : AppCompatActivity() {

    private lateinit var functions: Functions
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_in_detail)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        functions = Functions()

        val previousIntent = intent
        val LoanID = previousIntent.getIntExtra("LOANID",0)
        println("Received ".plus(LoanID))


        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)

        loanInstitutionViewModel.setLoanID(LoanID)

        loanInstitutionViewModel.specificLoanDetail.observe(this, Observer { loans ->
            println(loans.size)
            println(loans.get(0).loanName)


            val institutionName = findViewById<TextView>(R.id.loanDetailLoanInstitutionName)
            val institutionLogo = findViewById<ImageView>(R.id.loanDetailLoanInstitutionLogo)
            val institutionWebsite = findViewById<TextView>(R.id.loanDetailInstitutionWebsite)
            val loanName = findViewById<TextView>(R.id.loanDetailLoanName)

            val loanEmail = findViewById<TextView>(R.id.loanDetailInstitutionEmail)
            val loanPhone = findViewById<TextView>(R.id.loanDetailInstitutionPhone)
            val loanDetails = findViewById<TextView>(R.id.loanDetailLoanDescription)
            val loanAmount = findViewById<TextView>(R.id.loanDetailLoanMaximumAmount)

            val loanInterestRate = findViewById<TextView>(R.id.loanDetailLoanInterestRate)
            val loanRepay = findViewById<TextView>(R.id.loanDetailLoanRepayment)
            //val loanPerCentFinance= findViewById<TextView>(R.id.loanDetail)
            //val loanMinCreditScore = findViewById<TextView>(R.id.loanMinCreditScoreLoanDetails)

            institutionName.text = loans.get(0).institution
            institutionLogo.setImageResource(loans.get(0).logo!!.toInt())
            loanName.text =loans.get(0).loanName
            loanEmail.text = loans.get(0).email
            loanPhone.text  = loans.get(0).phone
            institutionWebsite.text = loans.get(0).website
            loanAmount.text= "$".plus(functions.currencyFormatter(loans. get(0).loanAmount.toString()))
            loanDetails.text = loans.get(0).description


        })



    }
}