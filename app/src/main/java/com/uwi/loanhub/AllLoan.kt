package com.uwi.loanhub

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.SearchView

//import androidx.appcompat.widget.SearchView



class AllLoan : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.uwi.loanhub.R.layout.activity_all_loan)

        val loan_listView: ListView = findViewById(com.uwi.loanhub.R.id.loan_listViewInActivity) //Fetching the layout with the list of
        loan_listView.adapter = LoanViewAdapter(LoanListViewModel(), this) //
        initSearchWidgets()


    }

    fun initSearchWidgets() {
        val searchView: SearchView = findViewById(com.uwi.loanhub.R.id.searchInstitution)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(query: String?): Boolean {
                //
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                var loanList = LoanListViewModel().getLoanList()
                val filteredLoans = ArrayList<Loans> ()




                for ( loan in loanList)
                {

                    if (loan.institution.getinstituionName().toLowerCase().contains(query!!.toLowerCase()))
                    {
                        filteredLoans.add(loan)


                    }
                }

                val loan_listView:ListView = findViewById(com.uwi.loanhub.R.id.loan_listViewInActivity)
                loan_listView.adapter = newLoanViewAdapter(applicationContext,0, filteredLoans)
                return false
            }
        })

    }


}

