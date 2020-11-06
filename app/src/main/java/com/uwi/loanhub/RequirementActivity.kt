package com.uwi.loanhub

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.LoanInstitutionViewModel
import com.uwi.loanhub.models.LoanRequirementViewModel
import kotlinx.android.synthetic.main.activity_requirement.*

class RequirementActivity : AppCompatActivity() {

    lateinit var section1: View
    lateinit var section2:View
    lateinit var section3:View
    private lateinit var loanRequirementViewModel:LoanRequirementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirement)

        //loanRequirementViewModel = ViewModelProvider(this).get(LoanRequirementViewModel::class.java)

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