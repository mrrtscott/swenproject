package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.UserViewModel
import org.jetbrains.annotations.NotNull
import java.util.*
import kotlin.concurrent.schedule

class LoansSpecificToUser : AppCompatActivity() {

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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loans_specific_to_user)



        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        functions = Functions()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("USERNAME")
        println(parsedStringID)
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
                println(occupation + "OCCUPTATION")







            }
        })


        val recycleView = findViewById<RecyclerView>(R.id.loanSpecificToUserActivityRecycleView)
        val adapter = LoanListAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)




        loanInstitutionViewModel.loansSpecificToUser .observe(this, Observer { loans ->
            println("change")
            println(loans.size)


            loans?.let{ adapter.setLoan(it)}
        })















    }




}