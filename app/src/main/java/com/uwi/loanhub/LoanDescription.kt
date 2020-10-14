package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Institution(inputInstitution:String, inputEmail: String, inputPhone: String, inputBranch: Array<String>, inputLogo:Int ) {

    private var instituionName:String = inputInstitution
    private var email: String = inputEmail
    private var phone: String = inputPhone
    private var address: String = inputaddress
    private var logo: Int = inputLogo
    private var description: String = inputdescription


    fun getLogo (): Int {
        return logo
    }


    fun getinstituionName (): String {
        return instituionName
    }