package com.uwi.loanhub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.uwi.loanhub.AppConstants.CITY
import com.uwi.loanhub.AppConstants.PARISH
import com.uwi.loanhub.AppConstants.PASSWORD
import com.uwi.loanhub.AppConstants.USERNAME
import com.uwi.loanhub.AppConstants.WAIT_TIME
import com.uwi.loanhub.models.User
import com.uwi.loanhub.models.UserViewModel
import kotlinx.coroutines.Dispatchers
import java.util.*
import kotlin.concurrent.schedule


/**
 * This class manages the login mechanism for the application
 */
class LoginActivityNew : AppCompatActivity() {

    private lateinit var editText_username_Login_Activity: EditText
    private lateinit var editText_password_Login_Activity: EditText
    private lateinit var startWaitBar: ProgressBar




    private lateinit var signUpButton: Button
    private lateinit var loginButton: Button
    private lateinit var privacyPolicyButton:TextView
    private lateinit var termOfUseButton:TextView


    private lateinit var userViewModel: UserViewModel
    private lateinit var functions: Functions

    override fun onBackPressed() {
        super.onBackPressed()
        loginButton.isEnabled = true
        editText_username_Login_Activity.isEnabled = true
        editText_password_Login_Activity.isEnabled = true

    }

    /**
     * Ensures that the login button and the input texts are functional to the user after some function which have disabled them
     * @param hasFocus A boolean which indicates whether the focus has changed
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        loginButton.isEnabled = true
        editText_username_Login_Activity.isEnabled = true
        editText_password_Login_Activity.isEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // This ensures that the application will be in full screen


        functions = Functions()
        startWaitBar = findViewById(R.id.startWaitBar)
        privacyPolicyButton = findViewById(R.id.privacyPolicyButton)
        termOfUseButton = findViewById(R.id.privacyTermsButton)
        editText_username_Login_Activity = findViewById(R.id.userNameLoginActivity)
        editText_password_Login_Activity = findViewById(R.id.passwordLoginActivity)
        signUpButton = findViewById(R.id.signupLoginActivity)
        loginButton = findViewById(R.id.LoginButtonSignInActicity)


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        privacyPolicyButton.setOnClickListener {
            val intent:Intent = Intent(this, PrivacyPolicy::class.java)
            startActivity(intent)
        }


        termOfUseButton.setOnClickListener{
            val intent:Intent = Intent(this, TermsOfUse::class.java)
            startActivity(intent)
        }









        signUpButton.setOnClickListener {
            val intent: Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }






        userViewModel.userList


        loginButton.setOnClickListener {
            startWaitBar.visibility = View.VISIBLE
            loginButton.isEnabled = false
            editText_username_Login_Activity.isEnabled = false
            editText_password_Login_Activity.isEnabled = false

            //The user's password is being encrypted by another function
            userViewModel.inputArrayList(arrayListOf(editText_username_Login_Activity.text.toString(), functions.encryptSys(editText_password_Login_Activity.text.toString())))


            userViewModel.userList.observe(this, Observer { users ->
                if (users.size == 1) //This is a second factor check to ensure that only one user was found with the username and password combination
                {
                    val intent = Intent (this,LoansSpecificToUser::class.java )
                    intent.putExtra( USERNAME, users[0].username)
                    intent.putExtra( CITY, users[0].city)
                    intent.putExtra( PARISH, users[0].parish)
                    intent.putExtra( PASSWORD, functions.encryptSys(editText_password_Login_Activity.text.toString()))






                    Timer().schedule(WAIT_TIME) {

                        startActivity(intent)


                    }

                    //This function allows the database loading on the first time to properly populate
                    Toast.makeText(this, "Welcome ".plus(users.get(0).firstName.plus("! Please wait")), Toast.LENGTH_SHORT).show()
                    Handler().postDelayed(Runnable {
                        startWaitBar.visibility = View.GONE
                    },WAIT_TIME)








                }
                //Indicates when a username and password combination is wrong.
                else{
                    Toast.makeText(this, "Your username and password is incorrect", Toast.LENGTH_SHORT).show()
                    startWaitBar.visibility = View.GONE
                    loginButton.isEnabled = true
                    editText_username_Login_Activity.isEnabled = true
                    editText_password_Login_Activity.isEnabled = true

                }


            })









        }





    }




}