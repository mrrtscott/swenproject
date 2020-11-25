package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.uwi.loanhub.models.User

class Settings : AppCompatActivity() {
    lateinit var userName: TextView
    lateinit var userEmail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        userName = findViewById(R.id.settings_user_name)
        userEmail = findViewById(R.id.settings_user_email)
        val previousIntent = intent
        val user = previousIntent.getParcelableExtra<User>("USER")
        userName.text = user.firstName.plus(" ").plus(user.lastName)
        userEmail.text = user.email

    }
}