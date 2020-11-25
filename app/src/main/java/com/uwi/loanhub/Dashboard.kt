package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.TipsViewModel
import com.uwi.loanhub.models.UserViewModel
import java.util.*


import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.uwi.loanhub.models.User
import java.time.*

/**
 * The Dashboard class manages the user dashboard
 */
class Dashboard : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private lateinit var tipsViewModel: TipsViewModel
    private lateinit var progressbar:ProgressBar
    private lateinit var tipsTextView:TextView


    private lateinit var userFullNameTextView:TextView
    private lateinit var userUsername:TextView

    private lateinit var dashboardLogout:Button

    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 10000
    val random = Random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val previousIntent = intent

        //Gets the user data strcuture whcih has been based from the previous activity
        val user = previousIntent.getParcelableExtra<User>("USER")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.drawerLayout_dashboard)
        navView = findViewById(R.id.navView_dashboard)
        progressbar = findViewById(R.id.progressDashboard)

        dashboardLogout = findViewById(R.id.dashboardLogout)

        toggle = ActionBarDrawerToggle(this,drawerLayout,0,0)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


        //Used to connect the dashboard class to the database
        tipsViewModel = ViewModelProvider(this).get(TipsViewModel::class.java)

        //Text views
        userFullNameTextView = findViewById(R.id.userFullName)
        userUsername = findViewById(R.id.userUsername)
        tipsTextView = findViewById(R.id.dashboardTips)






        //A concatenation of the user first name and last name. The line below sets the result to the relevant text view
        userFullNameTextView.text = user.firstName.plus(" ").plus(user.lastName)
        userUsername.text = user.username

        //Log out button which when triggered logs out a user from the application
        dashboardLogout.setOnClickListener {
            val intent = Intent(this, LoginActivityNew::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }



        var endTime = Instant.now().plusSeconds( 9 )

        //This ensures that the progress bar stop showing after the specified number of seconds
        Handler().postDelayed(Runnable {
            progressbar.visibility = View.INVISIBLE
        }, 9900)





        //This function randomly generates the tips which are to be shown to the user
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())


            tipsViewModel.allTips.observe(this, Observer { tips ->
                tipsTextView.text = tips[(0..tips.size-1).random()].tip


            })

        }.also { runnable = it }, delay.toLong())


    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
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
                Toast.makeText(this,"Dashboard", Toast.LENGTH_SHORT).show()}

            R.id.menu_loans -> {

                Toast.makeText(this,"Please go to home for loans", Toast.LENGTH_SHORT).show()}

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

