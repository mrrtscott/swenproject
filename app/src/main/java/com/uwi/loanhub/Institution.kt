package com.uwi.loanhub

class Institution(inputInstitution:String, inputEmail: String, inputPhone: String, inputBranch: Array<String>, inputLogo:Int ) {

    private var instituionName:String = inputInstitution
    private var email: String = inputEmail
    private var phone: String = inputPhone
    private var branch: Array<String> = inputBranch
    private var logo: Int = inputLogo


    /*
    A public function which is responsible for returning the ID of a logo to be used for resource purposes
    @return Int: logoID
    */
    fun getLogo (): Int {
        return logo
    }

    /*
    A public function which returns the name an institution
    @return String: Institution Name
     */
    fun getinstituionName (): String {
        return instituionName
    }


    fun getEmail(): String {
        return email
    }


    fun getPhone (): String {
        return phone
    }

    fun getBranch (): Array<String>{
        return branch
    }






}