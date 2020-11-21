package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.UserViewModel
import org.w3c.dom.Text

class CompareLoans : AppCompatActivity() {



    private lateinit var userViewModel: UserViewModel
    private var function:Functions = Functions ()
    private var loans:LoansSpecificToUser = LoansSpecificToUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_loans)



        var institutionNameLoanOne:TextView = findViewById(R.id.loanOneInstitutionName)
        var institutionNameLoanTwo:TextView = findViewById(R.id.loanTwoInstitutionName)
        var loanAmountLoanOne:TextView = findViewById(R.id.loanOneLoanAmount)
        var loanAmountLoanTwo:TextView = findViewById(R.id.loanTwoLoanAmount)
        var loanInterestRateLoanOne:TextView= findViewById(R.id.loanOneInterestRate)
        var loanInterestRateLoanTwo:TextView= findViewById(R.id.loanTwoInterestRate)

        var loanTermsOfRepaymentLoanOne:TextView = findViewById(R.id.loanOneTermsOfRepayment)
        var loanTermsOfRepaymentLoanTwo:TextView = findViewById(R.id.loanTwoTermsOfRepayment)

        var loanPercentFinanceLoanOne:TextView = findViewById(R.id.loanOnePercentFinancing)
        var loanPercentFinanceLoanTwo:TextView = findViewById(R.id.loanTwoPercentFinancing)

        var loanMinimumCreditScoreLoanOne:TextView = findViewById(R.id.loanOneMinimumCreditScore)
        var loanMinimumCreditScoreLoanTwo:TextView = findViewById(R.id.loanTwoMinimumCreditScore)

        var loanPickLoanOne:ImageView = findViewById(R.id.loanOnePick)
        var loanPickLoanTwo:ImageView = findViewById(R.id.loanTwoPick)


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

                institutionNameLoanOne.text= loanOne.institution
                institutionNameLoanTwo.text = loanTwo.institution
                loanAmountLoanOne.text = ("$"+loanOne.loanAmount)
                loanAmountLoanTwo.text = ("$"+loanTwo.loanAmount)
                loanInterestRateLoanOne.text = (loanOne.interestRate.toString() +"%")
                loanInterestRateLoanTwo.text = (loanTwo.interestRate.toString() +"%")

                loanTermsOfRepaymentLoanOne.text = (loanOne.termsRepay)
                loanTermsOfRepaymentLoanTwo.text = (loanTwo.termsRepay)

                loanPercentFinanceLoanOne.text = loanOne.percentFinancing.toString() +"%"
                loanPercentFinanceLoanTwo.text = loanTwo.percentFinancing.toString() +"%"

                loanMinimumCreditScoreLoanOne.text = loanOne.creditScore.toString()
                loanMinimumCreditScoreLoanTwo.text = loanTwo.creditScore.toString()

                if(recommendedLoan == 0){
                    loanPickLoanOne.visibility = View.VISIBLE
                } else{
                    loanPickLoanTwo.visibility = View.VISIBLE
                }





                println("Recommended Loan: $recommendedLoan")





            }




        })



        //recommendedLoan = function.loanRecommendation(loanInstitutionViewModel.loansSpecificToUser.value!!.get(loanOne.toInt()), loanInstitutionViewModel.loansSpecificToUser.value!!.get(loanTwo.toInt()), ))








    }
}