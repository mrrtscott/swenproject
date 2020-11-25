package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

/**
 * Used to manage the start up section of the application
 */
class Start_Up_Activity : AppCompatActivity() {

    private lateinit var startButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start__up_)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        startButton = findViewById(R.id.startActivityButton)

        startButton.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivityNew::class.java)
            startActivity(intent)
        }



    }
}