package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoansSpecificToUser : AppCompatActivity() {

    private lateinit var username:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loans_specific_to_user)

        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("USERNAME")
        username = parsedStringID


    }
}