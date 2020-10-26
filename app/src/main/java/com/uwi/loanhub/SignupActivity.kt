package com.uwi.loanhub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.uwi.loanhub.models.User
import com.uwi.loanhub.models.UserViewModel
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.experimental.and

class SignupActivity : AppCompatActivity() {
    private lateinit var editText_First_Name_Main_Activity: EditText
    private lateinit var editText_Last_Name_Main_Activity: EditText
    private lateinit var editText_email_SignUp_Activity: EditText
    private lateinit var editText_username_SignUp_Activity: EditText
    private lateinit var editText_password_SignUp_Activity: EditText
    private lateinit var editText_confirmPassword_SignUp_Activity: EditText
    private lateinit var editText_sex_SignUp_Activity: EditText
    private lateinit var editText_dob_SignUp_Activity: EditText
    private lateinit var editText_salary_SignUp_Activity: EditText
    private lateinit var drop_text_city: EditText
    private lateinit var drop_text_parish: EditText
    private lateinit var editText_primaryBank_SignUp_Activity: EditText
    private lateinit var editText_loanType_SignUp_Activity: EditText
    private lateinit var editText_loanAmount_Activity: EditText
    private lateinit var editText_occupation_SignUp_Activity: EditText
    private lateinit var functions: Functions




    private lateinit var buttons_start_main_act: Button
    private lateinit var buttonDateChooser: Button

    private lateinit var userViewModel: UserViewModel






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_activity)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        functions = Functions()





        editText_First_Name_Main_Activity = findViewById(R.id.editText_First_Name_Main_Activity)
        editText_Last_Name_Main_Activity = findViewById(R.id.editText_Last_Name_Main_Activity)
        editText_email_SignUp_Activity = findViewById(R.id.editText_email_Main_Activity)
        editText_username_SignUp_Activity = findViewById(R.id.editText_User_Name_Main_Activity)
        editText_password_SignUp_Activity = findViewById(R.id.editText_password_Main_Activity)
        editText_confirmPassword_SignUp_Activity = findViewById(R.id.editText_password_Confirm_Main_Activity)
        editText_sex_SignUp_Activity = findViewById(R.id.drop_text_sex)
        editText_dob_SignUp_Activity = findViewById(R.id.editText_dob_Main_Activity)
        editText_salary_SignUp_Activity = findViewById(R.id.editText_salary_Main_Activity)
        drop_text_city = findViewById(R.id.drop_text_city)
        drop_text_parish = findViewById(R.id.drop_text_parish)
        editText_primaryBank_SignUp_Activity = findViewById(R.id.editText_priBank_Main_Activity)
        editText_loanType_SignUp_Activity = findViewById(R.id.editText_loan_Type_Main_Activity)
        editText_loanAmount_Activity = findViewById(R.id.editText_loan_Amount_Main_Activity)
        editText_occupation_SignUp_Activity = findViewById(R.id.editText_occupation_Main_Activity)

        //Initialising buttons
        buttons_start_main_act = findViewById(R.id.buttons_start_main_act)
        buttonDateChooser =  findViewById(R.id.DateButtonChooser)

        //Button for the the date chooser on the Sign Up Activity
        buttonDateChooser.setOnClickListener {

            val builder : MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
            val picker : MaterialDatePicker<*> = builder.build()
            picker.show(supportFragmentManager, picker.toString())

            picker.addOnPositiveButtonClickListener {


                editText_dob_SignUp_Activity.setText(picker.headerText.toString())


            }

        }







        buttons_start_main_act.setOnClickListener {



            if(editText_First_Name_Main_Activity.text.toString().isEmpty()  || editText_Last_Name_Main_Activity.text.toString().isEmpty() || drop_text_city.text.toString().isEmpty() || drop_text_parish.text.toString().isEmpty())
            {
                Toast.makeText(this, "Please enter fill all the fields", Toast.LENGTH_SHORT).show()
            }
            if(editText_password_SignUp_Activity.text.toString() != editText_confirmPassword_SignUp_Activity.text.toString()){

                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()

            }
            else
            {
                val user = User(0,
                    editText_First_Name_Main_Activity.text.toString().trim(),
                    editText_Last_Name_Main_Activity.text.toString().trim(),
                    editText_email_SignUp_Activity.text.toString().trim(),
                    editText_username_SignUp_Activity.text.toString().trim(),
                    functions.encryptSys(editText_password_SignUp_Activity.text.toString().trim()),
                    editText_sex_SignUp_Activity.text.toString().trim(),
                    editText_dob_SignUp_Activity.text.toString().trim(),
                    editText_salary_SignUp_Activity.text.toString().toDouble(),
                    drop_text_city.text.toString(),
                    drop_text_parish.text.toString(),
                    editText_primaryBank_SignUp_Activity.text.toString(),
                    editText_loanType_SignUp_Activity.text.toString(),
                    editText_loanAmount_Activity.text.toString().toDouble(),
                    editText_occupation_SignUp_Activity.text.toString(),
                    functions.getCurrentDate())
                userViewModel.addUser(user)
                val intent = Intent (this,LoginActivityNew::class.java )
                startActivity(intent)
            }
        }






        /* For city drop down on the main activity*/
        var cityTowns: TextInputLayout = findViewById<TextInputLayout>(R.id.city_TextInputLayout)
        val cities =resources.getStringArray(R.array.cities) //Drop Down Items
        val autoCompleteCityText = findViewById<AutoCompleteTextView>(R.id.drop_text_city)
        val aa = ArrayAdapter(this, R.layout.dropdown_city_towns, cities)
        aa.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteCityText!!.setAdapter(aa)

        /* For city drop down on the main activity*/

        /* For Parish drop down on the main activity*/
        var parishLayout: TextInputLayout = findViewById<TextInputLayout>(R.id.parish_TextInputLayout)
        val parishes =resources.getStringArray(R.array.parishes) //Drop Down Items
        val autoCompleteParishText = findViewById<AutoCompleteTextView>(R.id.drop_text_parish)
        val parishesOut = ArrayAdapter(this, R.layout.dropdown_parish, parishes)
        parishesOut.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteParishText!!.setAdapter(parishesOut)

        /* For parish drop down on the main activity*/

        /* For sex drop down on sign up activity*/

        val sex =resources.getStringArray(R.array.sex) //Drop Down Items
        val autoCompleteSexText = findViewById<AutoCompleteTextView>(R.id.drop_text_sex)
        val sexOut = ArrayAdapter(this, R.layout.dropdown_sex, sex)
        sexOut.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteSexText!!.setAdapter(sexOut)

        /* For sex drop down on sign up activity*/











    }





    //Test







}