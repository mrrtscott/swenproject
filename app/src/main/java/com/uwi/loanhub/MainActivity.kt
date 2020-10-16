package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        loginButton = findViewById(R.id.login_btn)

        loginButton.setOnClickListener {
            //Toast.makeText(this, "Button working", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this, login_activity::class.java)
            startActivity(intent)

        }

    }


    //Test


}