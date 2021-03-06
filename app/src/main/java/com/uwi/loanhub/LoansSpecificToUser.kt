package com.uwi.loanhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.UserViewModel

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.uwi.loanhub.AppConstants.CHECKED
import com.uwi.loanhub.AppConstants.CITY
import com.uwi.loanhub.AppConstants.LOANID
import com.uwi.loanhub.AppConstants.LOANID_LOANONE
import com.uwi.loanhub.AppConstants.LOANID_LOANTWO
import com.uwi.loanhub.AppConstants.LOAN_ONE
import com.uwi.loanhub.AppConstants.LOAN_TWO
import com.uwi.loanhub.AppConstants.PARISH
import com.uwi.loanhub.AppConstants.PASSWORD
import com.uwi.loanhub.AppConstants.UNCHECKED
import com.uwi.loanhub.AppConstants.USER
import com.uwi.loanhub.AppConstants.USERNAME
import com.uwi.loanhub.models.User


/**
 * A class which manages loan which are specific to the current user of the application
 */
class LoansSpecificToUser : AppCompatActivity(), OnLoanClickListener, OnCompareLoanClickListener, NavigationView.OnNavigationItemSelectedListener{

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private lateinit var username:String
    private lateinit var receivedPassword:String
    private lateinit var userViewModel: UserViewModel
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    private lateinit var functions: Functions
    private var compareList: ArrayList<Int> = arrayListOf()
    private var listOfLoans: MutableList<LoanInstitution> = mutableListOf<LoanInstitution>()
    private var listOfUser: MutableList<User> = mutableListOf<User>()


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
    private var receivedCity:String = ""
    private var receivedParish:String = ""



    private lateinit var otherButton: Button
    private lateinit var viewComparisonButton :Button
    private lateinit var dashboardButton:Button
    private lateinit var counterText:TextView


    fun getLoanList():List<LoanInstitution>  {
        return listOfLoans
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loans_specific_to_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.drawerLayout_loansSpecificToUser)
        navView = findViewById(R.id.navView_loansSpecificToUser)

        toggle = ActionBarDrawerToggle(this,drawerLayout,0,0)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)



        otherButton = findViewById(R.id.otherLoansButton)
        viewComparisonButton = findViewById(R.id.viewComparison)
        dashboardButton = findViewById(R.id.dashboardButton)
        counterText= findViewById(R.id.counterLoan)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        //Fetching data data from the previous class
        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra(USERNAME)
        receivedPassword = previousIntent.getStringExtra(PASSWORD)
        receivedCity = previousIntent.getStringExtra(CITY)
        receivedParish = previousIntent.getStringExtra(PARISH)





        username = parsedStringID


        userViewModel.inputArrayList(arrayListOf(username, receivedPassword))


        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)

        //Sending data to the other User Loans Activity
        otherButton.setOnClickListener{
            val intent: Intent = Intent(this, UserLoansActivity::class.java)
            intent.putExtra(USERNAME, username)
            intent.putExtra(PASSWORD, receivedPassword)
            intent.putExtra(CITY, receivedCity)
            intent.putExtra(PARISH, receivedParish)
            startActivity(intent)
        }

        dashboardButton.setOnClickListener {
            val intent: Intent = Intent(this, Dashboard::class.java)
            intent.putExtra(USER, listOfUser[0])
            startActivity(intent)
        }


        userViewModel.userList.observe(this, Observer { singleUser ->

            //A second factor check to ensure that only one user has been identified
            if (singleUser.size == 1) {
                for (user in singleUser){
                    listOfUser.add(user)
                }
                firstName = singleUser[0].firstName
                lastName = singleUser[0].lastName
                email = singleUser[0].email
                password = singleUser[0].password
                sex = singleUser[0].sex
                dob = singleUser[0].dob
                salary = singleUser[0].salary
                creditScore = singleUser[0].creditScore
                city = singleUser[0].city
                parish = singleUser[0].parish
                primaryBank = singleUser[0].primaryBank
                loanType = singleUser[0].loanType
                loanAmount = singleUser[0].loanAmount
                occupation = singleUser[0].occupation
                loanInstitutionViewModel.getLoanInstitutionUserSpecific(
                    sex.substring(0),
                    creditScore,
                    loanAmount.toInt()
                )


            }
        })


        val recycleView = findViewById<RecyclerView>(R.id.loanSpecificToUserActivityRecycleView)
        val adapter = LoanListAdapter(this, this, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)




        loanInstitutionViewModel.loansSpecificToUser .observe(this, Observer { loans ->

            counterText.text = loans.size.toString()
            compareList.clear() //Clears the list of loans for comparison just in case the loan position has been changed.


            loans?.let { adapter.setLoan(it) }

            //adding loans to a a list of loans which are manipulable and can be transferred
            for (loan in loans) {
                listOfLoans.add(loan)
            }




        })


        //When the loan comparator is selected, this function checks to ensure that there are exactly two loans to be compared
        viewComparisonButton.setOnClickListener {
            loanInstitutionViewModel.loansSpecificToUser.observe(this, Observer { loans ->
                if (compareList.size == 2) {
                    val intent: Intent = Intent(this, CompareLoans::class.java)
                    intent.putExtra(LOANID_LOANONE, loans[compareList[0]].id.toString())
                    intent.putExtra(LOANID_LOANTWO, loans[compareList[1]].id.toString())
                    intent.putExtra(USERNAME, username)
                    intent.putExtra(PASSWORD, receivedPassword)
                    intent.putExtra(LOAN_ONE, loans[compareList[0]])
                    intent.putExtra(LOAN_TWO, loans[compareList[1]])

                    startActivity(intent)
                } else{
                    Toast.makeText(this,"You must compare two loans only", Toast.LENGTH_SHORT).show()
                }


            })
        }









    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home -> {
                Log.i("Toast","home")
                Toast.makeText(this,"You are home",Toast.LENGTH_SHORT).show()
            }

            R.id.menu_db -> {
                val intent:Intent = Intent(this, Dashboard::class.java)
                intent.putExtra("USER", listOfUser[0])
                startActivity(intent)}

            R.id.menu_loans -> {

                val intent: Intent = Intent(this, UserLoansActivity::class.java)
                intent.putExtra(USERNAME, username)
                intent.putExtra(PASSWORD, receivedPassword)
                intent.putExtra(CITY, receivedCity)
                intent.putExtra(PARISH, receivedParish)
                startActivity(intent) }

            R.id.menu_settings -> {
                val intent:Intent = Intent(this, Settings::class.java)
                intent.putExtra("USER", listOfUser[0])
                startActivity(intent)}

            R.id.menu_profile_update -> Toast.makeText(this,
                "profile", Toast.LENGTH_SHORT).show()

            R.id.menu_logout -> {
                val intent:Intent = Intent(this, LoginActivityNew::class.java)
                startActivity(intent)}

            else -> Log.d(this.toString(),item.itemId.toString())



        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    /**
     * An implementation of a method which will be used to identify the position of the loan which was clicked by the user
     * @param position The position of the loan in the list
     */
    override fun onLoanItemClicked(position: Int) {
        loanInstitutionViewModel.loansSpecificToUser.observe(this, Observer { loans ->
            println(loans[position].id)
            val intent: Intent = Intent(this, LoanInDetail::class.java)
            intent.putExtra(LOANID, loans[position].id)
            intent.putExtra(USERNAME, username)
            intent.putExtra(CITY, receivedCity)
            intent.putExtra(PARISH, receivedParish)
            startActivity(intent)
        })
    }


    /**
     * An implementation of a method which will be used to identify the position of a loan and the action which was taken by the user
     * @param position The position of the loan in the list
     * @param action The action which was taken by the user whether checked or unchecked
     */
    override fun onLoanCompareItemClicked(position: Int, action: String) {



        if (compareList.size > 1 && action.equals(CHECKED)){
            Toast.makeText(this, "Too many loans selected", Toast.LENGTH_LONG).show()
        } else if(compareList.size < 3 && action.equals(CHECKED)){
            if(!compareList.contains(position)){
                compareList.add(position)
            }

        }

        if(action.equals(UNCHECKED)){
            compareList.remove(position)
        }






    }




}