package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class login_activity : AppCompatActivity() {
    private lateinit var backButton: ImageView
    private lateinit var loginButton: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)
        backButton = findViewById(R.id.back_arrow_white);
        loginButton = findViewById(R.id.main_login_btn);
        username = findViewById(R.id.usr_username)
        password = findViewById(R.id.usr_password)


        backButton.setOnClickListener {
            var intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            if(username.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter your info", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"Welcome " + username.text.toString(), Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this,AllLoan::class.java)
                startActivity(intent)
            }
        }

    }
}