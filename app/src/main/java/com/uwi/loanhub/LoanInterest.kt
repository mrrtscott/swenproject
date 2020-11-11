package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitutionViewModel

class LoanInterest : AppCompatActivity() {

    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    private var sentArrayLoanInstitution:ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_interest)
        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)

        val recycleView = findViewById<RecyclerView>(R.id.loanInterestActivityRecycleView)
        val adapter = LikeLoanListAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        val previousIntent = intent
        val LoanID = previousIntent.getIntExtra("LOANID", 0)
        val username = previousIntent.getStringExtra("USERNAME")



        sentArrayLoanInstitution.add(LoanID.toString())
        sentArrayLoanInstitution.add(username)

        loanInstitutionViewModel.setArray(sentArrayLoanInstitution)

        loanInstitutionViewModel.loanInstitutionLikesRating.observe(this, Observer { loans ->

            loans?.let{ adapter.setLoan(it)}

        })




    }
}