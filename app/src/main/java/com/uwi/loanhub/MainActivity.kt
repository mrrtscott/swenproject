package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner

import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* For city drop down on the main activity*/
        var cityTowns:TextInputLayout = findViewById<TextInputLayout>(R.id.city_TextInputLayout)
        var  cities =resources.getStringArray(R.array.cities) //Drop Down Items
        var autoCompleteCityText = findViewById<AutoCompleteTextView>(R.id.drop_text_city)
        var aa = ArrayAdapter(this, R.layout.dropdown_city_towns, cities)
        aa.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteCityText!!.setAdapter(aa)

        /* For city drop down on the main activity*/

        /* For Parish drop down on the main activity*/
        var parishLayout:TextInputLayout = findViewById<TextInputLayout>(R.id.parish_TextInputLayout)
        var parishes =resources.getStringArray(R.array.parishes) //Drop Down Items
        var autoCompleteParishText = findViewById<AutoCompleteTextView>(R.id.drop_text_parish)
        var parishesOut = ArrayAdapter(this, R.layout.dropdown_parish, parishes)
        parishesOut.setDropDownViewResource(android.R.layout.simple_list_item_1)
        autoCompleteParishText!!.setAdapter(parishesOut)

        /* For parish drop down on the main activity*/






    }



    //Test


}