package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.LoanViewModel

class UserLoansActivity : AppCompatActivity() {

    private lateinit var loanViewModel: LoanViewModel
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_loans)
        val recycleView = findViewById<RecyclerView>(R.id.userLoansActivityRecycleView)
        val adapter = LoanListAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)

        loanInstitutionViewModel.allLoanInstitution.observe(this, Observer { loans ->
            loans?.let{ adapter.setLoan(it)

            }
        })
    }
}