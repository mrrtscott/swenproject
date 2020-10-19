package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LoginActivityNew : AppCompatActivity() {
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        signUpButton = findViewById(R.id.signupLoginActivity)
        signUpButton.setOnClickListener {
            val intent: Intent = Intent(this, Signup_Activity::class.java)
            startActivity(intent)
        }
    }
}