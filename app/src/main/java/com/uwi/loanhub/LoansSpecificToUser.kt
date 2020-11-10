package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.UserViewModel
import org.jetbrains.annotations.NotNull
import java.util.*
import kotlin.concurrent.schedule

class LoansSpecificToUser : AppCompatActivity(), OnLoanClickListener {

    private lateinit var username:String
    private lateinit var userViewModel: UserViewModel
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    private lateinit var functions: Functions


    private  var firstName: String = ""
    private  var lastName: String= ""
    private  var email: String= ""
    private  var password: String= ""
    private  var sex: String= ""
    private  var dob: String= ""
    private var salary: Double = 0.00
    private var creditScore: Int = 0
    private var city:String= ""
    private  var parish: String= ""
    private  var primaryBank: String= ""
    private  var loanType: String= ""
    private var loanAmount: Double = 0.00
    private  var occupation: String= ""



    private lateinit var otherButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loans_specific_to_user)



        otherButton = findViewById(R.id.otherLoansButton)
        otherButton.setOnClickListener{
            val intent: Intent = Intent(this, UserLoansActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("USERNAME")



        username = parsedStringID


        userViewModel.getUser(username)


        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)


        userViewModel.singleUser.observe(this, Observer { singleUser ->



            if(singleUser.size ==  1)

            {
                firstName = singleUser[0].firstName
                lastName = singleUser[0].lastName
                email = singleUser[0].email
                password = singleUser[0].password
                sex = singleUser[0].sex
                dob = singleUser[0].dob
                salary = singleUser[0].salary
                creditScore = singleUser[0].creditScore
                city  = singleUser[0].city
                parish = singleUser[0].parish
                primaryBank = singleUser[0].primaryBank
                loanType =  singleUser[0].loanType
                loanAmount = singleUser[0].loanAmount
                occupation = singleUser[0].occupation
                loanInstitutionViewModel.getLoanInstitutionUserSpecific(sex.substring(0), creditScore, loanAmount.toInt())








            }
        })


        val recycleView = findViewById<RecyclerView>(R.id.loanSpecificToUserActivityRecycleView)
        val adapter = LoanListAdapter(this, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)




        loanInstitutionViewModel.loansSpecificToUser .observe(this, Observer { loans ->
            println("change")
            println(loans.size)



            loans?.let{ adapter.setLoan(it)}
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