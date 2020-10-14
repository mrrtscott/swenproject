package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var signupButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = findViewById(R.id.login_btn)
        signupButton = findViewById(R.id.signup_link)

        loginButton.setOnClickListener {
            Toast.makeText(this,"Button working", Toast.LENGTH_SHORT).show()
            val intent:Intent = Intent(this,login_activity::class.java)
            startActivity(intent)

        }

        signupButton.setOnClickListener {
            val intent = Intent(this,signup_activity::class.java)
            startActivity(intent)
        }

    }


    //Test


}