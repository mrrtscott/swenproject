package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        })



    }
}