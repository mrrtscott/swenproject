package com.uwi.loanhub

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.LoanRequirementViewModel
import kotlinx.android.synthetic.main.activity_requirement.*

class RequirementActivity : AppCompatActivity() {

    lateinit var section1: View
    lateinit var section2:View
    lateinit var section3:View
    private lateinit var loanRequirementViewModel:LoanRequirementViewModel
    val inputArrayList:ArrayList<String> = arrayListOf()

    lateinit var identificationTextView: TextView
    lateinit var employmentTextView: TextView
    lateinit var characterTextView: TextView
    lateinit var vehicleTextView: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirement)

        val previousIntent = intent
        val receivedInstitution = previousIntent.getStringExtra("INSTITUTION")

        loanRequirementViewModel = ViewModelProvider(this).get(LoanRequirementViewModel::class.java)

        inputArrayList.clear()
        inputArrayList.add(receivedInstitution)

        loanRequirementViewModel.setArray(inputArrayList)

        section1 = findViewById(R.id.section1)
        section2 = findViewById(R.id.section2)
        section3 = findViewById(R.id.section3)

        identificationTextView = findViewById(R.id.identificationInformationRequirement)
        employmentTextView = findViewById(R.id.employmentInformationRequirement)
        characterTextView = findViewById(R.id.characterInformationRequirement)
        vehicleTextView = findViewById(R.id.vehicleInformationRequirement)



        val header1 = findViewById<TextView>(R.id.header1)

        header1.setOnClickListener{
            if (section1.visibility === View.GONE) {
                section1.visibility = View.VISIBLE
            } else {
                section1.visibility = View.GONE
            }
        }

        header2.setOnClickListener{
            if (section2.visibility === View.GONE) {
                section2.visibility = View.VISIBLE
            } else {
                section2.visibility = View.GONE
            }
        }

        header3.setOnClickListener{
            if (section3.visibility === View.GONE) {
                section3.visibility = View.VISIBLE
            } else {
                section3.visibility = View.GONE
            }
        }

        header4.setOnClickListener{
            if (section4.visibility === View.GONE) {
                section4.visibility = View.VISIBLE
            } else {
                section4.visibility = View.GONE
            }
        }


        loanRequirementViewModel.allLoanRequirement.observe(this, Observer { requirements ->
            identificationTextView.text =  requirements.get(0).identification
            employmentTextView.text = requirements.get(0).employment
            characterTextView.text = requirements.get(0).character
            vehicleTextView.text = requirements.get(0).vehicle

        })














    }
}