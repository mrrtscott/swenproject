package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.UserViewModel

class LoginActivityNew : AppCompatActivity() {

    private lateinit var editText_username_SignUp_Activity: EditText
    private lateinit var editText_password_SignUp_Activity: EditText




    private lateinit var signUpButton: Button
    private lateinit var loginButton: Button


    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        editText_username_SignUp_Activity = findViewById(R.id.editText_User_Name_Main_Activity)
        editText_password_SignUp_Activity = findViewById(R.id.editText_password_Main_Activity)


        signUpButton = findViewById(R.id.signupLoginActivity)
        signUpButton.setOnClickListener {
            val intent: Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }






        loginButton = findViewById(R.id.LoginButtonSignInActicity)
        loginButton.setOnClickListener {

            userViewModel.getUsernamePassword(editText_username_SignUp_Activity.text.toString(), editText_password_SignUp_Activity.text.toString())




        }





    }
}