package com.uwi.loanhub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.User
import com.uwi.loanhub.models.UserViewModel

class LoginActivityNew : AppCompatActivity() {

    private lateinit var editText_username_Login_Activity: EditText
    private lateinit var editText_password_Login_Activity: EditText




    private lateinit var signUpButton: Button
    private lateinit var loginButton: Button


    private lateinit var userViewModel: UserViewModel
    private lateinit var functions: Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        functions = Functions()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        editText_username_Login_Activity = findViewById(R.id.userNameLoginActivity)
        editText_password_Login_Activity = findViewById(R.id.passwordLoginActivity)


        signUpButton = findViewById(R.id.signupLoginActivity)
        signUpButton.setOnClickListener {
            val intent: Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }






        loginButton = findViewById(R.id.LoginButtonSignInActicity)
        loginButton.setOnClickListener {
            var userHolderList:List<User> = userViewModel.getUsernamePassword(editText_username_Login_Activity.text.toString(), functions.encryptSys(editText_password_Login_Activity.text.toString()))
            println(userHolderList.size)
            if (userHolderList.size == 1)
            {

            }






        }





    }




}