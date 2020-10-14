package com.uwi.loanhub

import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup

//import androidx.appcompat.widget.SearchView

//import androidx.appcompat.widget.SearchView



class AllLoan : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.uwi.loanhub.R.layout.activity_all_loan)

        val loan_listView: ListView = findViewById(com.uwi.loanhub.R.id.loan_listViewInActivity) //Fetching the layout with the list of loans
        loan_listView.adapter = LoanViewAdapter(LoanListViewModel(), this) //This feeds information into the list view from the loans list model which contains the intital list of loans
        initSearchWidgets()



    }
    /*
    The function which is responsible for carrying out the search on the loan products based on the user's input
     */
    fun initSearchWidgets() {
        val searchView: SearchView = findViewById(com.uwi.loanhub.R.id.searchInstitution)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                var loanList = LoanListViewModel().getLoanList()
                val filteredLoans = ArrayList<Loans> () //this array list is meant to store filtered loans based on the search query from the user

                for ( loan in loanList)
                {

                    if (loan.institution.getInstitutionName().toLowerCase().contains(query!!.toLowerCase())) //This standardise the text of checking to prevent unintended omissions based on capitalisation
                    {
                        filteredLoans.add(loan)


                    }
                }

                val loan_listView:ListView = findViewById(com.uwi.loanhub.R.id.loan_listViewInActivity)
                loan_listView.adapter = newLoanViewAdapter(applicationContext,0, filteredLoans) // This is responsible for finally adding the filtered loan to the list view

                return false
            }
        })

    }


}

