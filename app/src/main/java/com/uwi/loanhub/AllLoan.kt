package com.uwi.loanhub

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class AllLoan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.uwi.loanhub.R.layout.activity_all_loan)

        val loan_listView:ListView = findViewById(com.uwi.loanhub.R.id.loan_listViewInActivity)
        loan_listView.adapter = LoanViewAdapter(LoanListViewModel(),this)













    }
}