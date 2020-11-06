package com.uwi.loanhub

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_requirement.*

class RequirementActivity : AppCompatActivity() {

    lateinit var section1: View
    lateinit var section2:View
    lateinit var section3:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirement)

        section1 = findViewById(R.id.section1)
        section2 = findViewById(R.id.section2)
        section3 = findViewById(R.id.section3)
        val header1 = findViewById<TextView>(R.id.header1)

        header1.setOnClickListener{
            if (section1.visibility === View.GONE) {
                section1.visibility = View.VISIBLE
            } else {
                section1.visibility = View.GONE
            }
        }

        header2.setOnClickListener{
            if (section1.visibility === View.GONE) {
                section1.visibility = View.VISIBLE
            } else {
                section1.visibility = View.GONE
            }
        }

        header3.setOnClickListener{
            if (section1.visibility === View.GONE) {
                section1.visibility = View.VISIBLE
            } else {
                section1.visibility = View.GONE
            }
        }














    }
}