package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.UserViewModel

class CompareLoans : AppCompatActivity() {



    private lateinit var userViewModel: UserViewModel
    private var function:Functions = Functions ()
    private var loans:LoansSpecificToUser = LoansSpecificToUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_loans)


        var itemsLayout: LinearLayout = findViewById(R.id.loanTable)
        var institutionNameLoanOne:TextView = findViewById(R.id.loanOneInstitutionName)
        var institutionNameLoanTwo:TextView = findViewById(R.id.loanTwoInstitutionName)
        var


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        val previousIntent = intent
        val username = previousIntent.getStringExtra("USERNAME")
        val password = previousIntent.getStringExtra("PASSWORD")
        val loanOneID = previousIntent.getStringExtra("LOANID_LOANONE")
        val loanTwoID = previousIntent.getStringExtra("LOANID_LOANTWO")
        val loanOne = previousIntent.getParcelableExtra<LoanInstitution>("LOAN_ONE")
        val loanTwo = previousIntent.getParcelableExtra<LoanInstitution>("LOAN_TWO")

        println(loanOneID)
        println(loanTwoID)

        userViewModel.inputArrayList(arrayListOf(username, password))

        userViewModel.singleUser.observe(this, Observer { users->
            if(users.size == 1){

                var recommendedLoan = function.loanRecommendation(loanOne, loanTwo, users[0])
                institutionName.text = loanOne.name
                println("Recommended Loan: $recommendedLoan")





            }




        })



        //recommendedLoan = function.loanRecommendation(loanInstitutionViewModel.loansSpecificToUser.value!!.get(loanOne.toInt()), loanInstitutionViewModel.loansSpecificToUser.value!!.get(loanTwo.toInt()), ))








    }
}