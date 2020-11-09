package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.LoanViewModel

class UserLoansActivity : AppCompatActivity(), OnLoanClickListener {

    /*

   This class is used to display all the loans that is in the database that are active

     */

    private lateinit var loanViewModel: LoanViewModel
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    var username:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_loans)

        val previousIntent = intent
        username = previousIntent.getStringExtra("USERNAME")

        println("USERNAME")
        println(username)

        val recycleView = findViewById<RecyclerView>(R.id.userLoansActivityRecycleView)
        val adapter = LoanListAdapter(this, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)

        loanInstitutionViewModel.allLoanInstitution.observe(this, Observer { loans ->
            loans?.let{ adapter.setLoan(it)

            }
        })
    }





    override fun onLoanItemClicked(position: Int) {
        loanInstitutionViewModel.loansSpecificToUser.observe(this, Observer {loans ->
            println(loans[position].id)
            val intent: Intent = Intent(this, LoanInDetail::class.java)
            intent.putExtra("LOANID", loans[position].id)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        })
    }
}