package com.uwi.loanhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.uwi.loanhub.models.User
import com.uwi.loanhub.models.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

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




    private lateinit var buttons_start_main_act: Button
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_activity)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)




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
        buttons_start_main_act = findViewById(R.id.buttons_start_main_act)





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
                    editText_First_Name_Main_Activity.text.toString(),
                    editText_Last_Name_Main_Activity.text.toString(),
                    editText_email_SignUp_Activity.text.toString(),
                    editText_username_SignUp_Activity.text.toString(),
                    editText_password_SignUp_Activity.text.toString(),
                    editText_sex_SignUp_Activity.text.toString(),
                    editText_dob_SignUp_Activity.text.toString(),
                    editText_salary_SignUp_Activity.text.toString().toDouble(),
                    drop_text_city.text.toString(),
                    drop_text_parish.text.toString(),
                    editText_primaryBank_SignUp_Activity.text.toString(),
                    editText_loanType_SignUp_Activity.text.toString(),
                    editText_loanAmount_Activity.text.toString().toDouble(),
                    editText_occupation_SignUp_Activity.text.toString(),
                    "1990-12-09")
                userViewModel.addUser(user)
                val intent = Intent (this,login_activity::class.java )
                startActivity(intent)
            }
        }



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

        /* For sex drop down on sign up activity*/

        var sex =resources.getStringArray(R.array.sex) //Drop Down Items
        var autoCompleteSexText = findViewById<AutoCompleteTextView>(R.id.drop_text_sex)
        var sexOut = ArrayAdapter(this, R.layout.dropdown_sex, sex)
        sexOut.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteSexText!!.setAdapter(sexOut)

        /* For sex drop down on sign up activity*/






    }





    //Test







}