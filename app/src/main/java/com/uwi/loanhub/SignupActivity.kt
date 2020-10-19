package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class SignupActivity : AppCompatActivity() {
    private lateinit var editText_First_Name_Main_Activity: EditText
    private lateinit var editText_Last_Name_Main_Activity: EditText
    private lateinit var drop_text_city: EditText
    private lateinit var drop_text_parish: EditText
    private lateinit var buttons_start_main_act: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_activity)

        editText_First_Name_Main_Activity = findViewById(R.id.editText_First_Name_Main_Activity)
        editText_Last_Name_Main_Activity = findViewById(R.id.editText_Last_Name_Main_Activity)
        drop_text_city = findViewById(R.id.drop_text_city)
        drop_text_parish = findViewById(R.id.drop_text_parish)
        buttons_start_main_act = findViewById(R.id.buttons_start_main_act)

        buttons_start_main_act.setOnClickListener {
            if(editText_First_Name_Main_Activity.text.toString().isEmpty()  || editText_Last_Name_Main_Activity.text.toString().isEmpty() || drop_text_city.text.toString().isEmpty() || drop_text_parish.text.toString().isEmpty())
            {
                Toast.makeText(this, "Please enter fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent (this,login_activity::class.java )
                startActivity(intent)
            }
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        /* For city drop down on the main activity*/
        var cityTowns: TextInputLayout = findViewById<TextInputLayout>(R.id.city_TextInputLayout)
        var  cities =resources.getStringArray(R.array.cities) //Drop Down Items
        var autoCompleteCityText = findViewById<AutoCompleteTextView>(R.id.drop_text_city)
        var aa = ArrayAdapter(this, R.layout.dropdown_city_towns, cities)
        aa.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteCityText!!.setAdapter(aa)

        /* For city drop down on the main activity*/

        /* For Parish drop down on the main activity*/
        var parishLayout: TextInputLayout = findViewById<TextInputLayout>(R.id.parish_TextInputLayout)
        var parishes =resources.getStringArray(R.array.parishes) //Drop Down Items
        var autoCompleteParishText = findViewById<AutoCompleteTextView>(R.id.drop_text_parish)
        var parishesOut = ArrayAdapter(this, R.layout.dropdown_parish, parishes)
        parishesOut.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteParishText!!.setAdapter(parishesOut)

        /* For parish drop down on the main activity*/






    }



    //Test







}