package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.AppConstants.LOANID
import com.uwi.loanhub.AppConstants.USERNAME
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.LoanInstitutionViewModel

class LoanInterest : AppCompatActivity(), OnLoanClickListener {

    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    private var sentArrayLoanInstitution:ArrayList<String> = arrayListOf()
    private lateinit var shareButton : Button

    lateinit var initialLoanList:List<LoanInstitution>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_interest)
        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)


        val recycleView = findViewById<RecyclerView>(R.id.loanInterestActivityRecycleView)
        val adapter = LikeLoanListAdapter(this, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        val previousIntent = intent
        val LoanID = previousIntent.getIntExtra(LOANID, 0)
        val username = previousIntent.getStringExtra(USERNAME)



        sentArrayLoanInstitution.add(LoanID.toString())
        sentArrayLoanInstitution.add(username)

        loanInstitutionViewModel.setArray(sentArrayLoanInstitution)

        loanInstitutionViewModel.loanInstitutionLikesRating.observe(this, Observer { loans ->
            initialLoanList = loans
            loans?.let{ adapter.setLoan(it)}


        })




    }


    override fun onLoanItemClicked(position: Int) {

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, initialLoanList[position].institution.plus('\n').plus(initialLoanList[position].loanName).plus('\n').plus(initialLoanList[position].description))
        startActivity(shareIntent)

    }


}