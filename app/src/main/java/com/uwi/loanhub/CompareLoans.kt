package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CompareLoans : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_loans)

        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("USERNAME")




    }
}