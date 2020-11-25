package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.LoanViewModel

import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.uwi.loanhub.models.User

class UserLoansActivity : AppCompatActivity(), OnLoanClickListener, OnCompareLoanClickListener, NavigationView.OnNavigationItemSelectedListener {

    /**
     *
     * This class is used to display all the loans that is in the database that are active
     *
     */

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private lateinit var loanViewModel: LoanViewModel
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    var username:String = ""
    var city:String = ""
    var parish:String = ""

    private var receivedPassword:String = " "

    private lateinit var viewComparson: Button

    private var compareList: ArrayList<Int> = arrayListOf()
    private var listOfLoans: MutableList<LoanInstitution> = mutableListOf<LoanInstitution>()
    private var listOfUser: MutableList<User> = mutableListOf<User>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_loans)

        viewComparson = findViewById(R.id.viewComparisonAllLoans)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.drawerLayout_user_loans)
        navView = findViewById(R.id.navView_userLoans)

        toggle = ActionBarDrawerToggle(this,drawerLayout,0,0)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        //Receiving values from previous activity
        val previousIntent = intent
        username = previousIntent.getStringExtra("USERNAME")
        receivedPassword = previousIntent.getStringExtra("PASSWORD")
        city = previousIntent.getStringExtra("CITY")
        parish = previousIntent.getStringExtra("PARISH")



        val recycleView = findViewById<RecyclerView>(R.id.userLoansActivityRecycleView)
        val adapter = AllLoanListAdapter(this, this, this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        //Comparison button that and a internal checker to ensure only two loans have been selected before operation can be performed
        viewComparson.setOnClickListener {
            loanInstitutionViewModel.allLoanInstitution.observe(this, Observer { loans ->
                if (compareList.size == 2) {
                    val intent: Intent = Intent(this, CompareLoans::class.java)
                    intent.putExtra("USERNAME", username)
                    intent.putExtra("PASSWORD", receivedPassword)
                    intent.putExtra("LOAN_ONE", loans[compareList[0]])
                    intent.putExtra("LOAN_TWO", loans[compareList[1]])
                    startActivity(intent)
                } else{
                    Toast.makeText(this,"You must compare two loans only", Toast.LENGTH_SHORT).show()
                }
            })


        }

        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)

        loanInstitutionViewModel.allLoanInstitution.observe(this, Observer { loans ->
            compareList.clear()
            loans?.let{ adapter.setLoan(it)
                //Adding each of the loan into another storage structure
                for (loan in loans) {
                    listOfLoans.add(loan)
                }


            }
        })
    }


    /**
     * This method is used to identify which loans have been clicked
     * @param position An integer which containing the position of the loan clicked
     */
    override fun onLoanItemClicked(position: Int) {
        loanInstitutionViewModel.allLoanInstitution .observe(this, Observer {loans ->

            val intent: Intent = Intent(this, LoanInDetail::class.java)
            intent.putExtra("LOANID", loans[position].id)
            intent.putExtra("USERNAME", username)
            intent.putExtra("CITY", city)
            intent.putExtra("PARISH", parish)
            startActivity(intent)
        })
    }

    /**
     *
     * @param position The position of the of the loan in the list
     * @param action This is the input of what action was performed whether checked or unchecked
     */
    override fun onLoanCompareItemClicked(position: Int, action:String) {

        if (compareList.size > 1 && action.equals("CHECKED")){
            Toast.makeText(this, "Too many loans selected", Toast.LENGTH_LONG).show()
        } else if(compareList.size < 3 && action.equals("CHECKED")){
            if(!compareList.contains(position)){
                compareList.add(position)
            }

        }

        if(action.equals("UNCHECKED")){
            compareList.remove(position)
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
                onBackPressed()
                /*val intent: Intent = Intent(this, LoansSpecificToUser::class.java)
                startActivity(intent)*/
            }

            R.id.menu_db -> {
                Log.i("Toast","home")
                Toast.makeText(this,"Please go to home for Dashboard", Toast.LENGTH_SHORT).show()}

            R.id.menu_loans -> {

                Toast.makeText(this,"loans", Toast.LENGTH_SHORT).show()}

            R.id.menu_settings -> Toast.makeText(this,
                "settings", Toast.LENGTH_SHORT).show()

            R.id.menu_profile_update -> Toast.makeText(this,
                "profile", Toast.LENGTH_SHORT).show()

            R.id.menu_logout -> {
                val intent: Intent = Intent(this, LoginActivityNew::class.java)
                startActivity(intent)}

            else -> Log.d(this.toString(),item.itemId.toString())



        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}