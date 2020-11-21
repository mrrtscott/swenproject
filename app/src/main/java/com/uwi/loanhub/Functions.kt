package com.uwi.loanhub

import android.os.Build
import androidx.annotation.RequiresApi
import com.uwi.loanhub.models.Loan
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.User
import java.security.MessageDigest
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.experimental.and
import kotlin.random.Random.*

class Functions {


    fun encryptSys(inputPassword: String): String {

        var md: MessageDigest = MessageDigest.getInstance("SHA-512")
        var digest = md.digest(inputPassword.toByteArray())
        var sb: StringBuilder = StringBuilder()

        var i = 0
        while (i < digest.count()) {
            sb.append(((digest[i] and 0xff.toByte()) + 0x100).toString(16).substring(0, 1))
            i++
        }

        return sb.toString()
    }


    fun getCurrentDate(): String{

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted

    }

    fun givenList_shouldReturnARandomElement():String  {
        val givenList = Arrays.asList("NCB", "First Global","Scotiabank", "Victoria Mutual", "JN Bank", "JMMB", "Sagicor", "CIBC")
        val rand = Random()
        val randomElement = givenList[rand.nextInt(givenList.size)]
        return randomElement
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun currencyFormatter(num: String): String? {
        val m = num.toDouble()
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(m)
    }

    fun loanRecommendation (inputLoanOne:LoanInstitution, inputLoanTwo:LoanInstitution, inputUser: User):Int{
        var loanOneTotal: Int = 0
        var loanTwoTotal: Int = 0
        val randomValues = List(1) { kotlin.random.Random.nextInt(0, 2) }

        val loanOneLoanToSalaryRatio = (inputLoanOne.loanAmount?.minus(inputUser.loanAmount))?.div(
            inputUser.salary
        )
        val loanTwoLoanToSalaryRatio = (inputLoanTwo.loanAmount?.minus(inputUser.loanAmount))?.div(
            inputUser.salary
        )



        if(inputLoanOne.institution!!.decapitalize() == inputUser.primaryBank.decapitalize()){
            loanOneTotal += 1
        }

        if(inputLoanTwo.institution!!.decapitalize() == inputUser.primaryBank.decapitalize()){
            loanOneTotal += 1
        }


        if(inputLoanOne.loanAmount!! > inputLoanTwo.loanAmount!!){
            loanOneTotal += 1
        }

        if(inputLoanOne.loanAmount!! < inputLoanTwo.loanAmount!!){
            loanTwoTotal += 1
        }

        if(inputLoanOne.loanAmount == inputLoanTwo.loanAmount){
            loanOneTotal += 1
            loanTwoTotal += 1
        }

        if (loanOneLoanToSalaryRatio != null) {
            if(loanOneLoanToSalaryRatio > loanTwoLoanToSalaryRatio!!){
                loanOneTotal += 1
            }
        }

        if (loanOneLoanToSalaryRatio != null) {
            if(loanOneLoanToSalaryRatio < loanTwoLoanToSalaryRatio!!){
                loanTwoTotal += 1
            }
        }

        if(loanOneLoanToSalaryRatio ==  loanTwoLoanToSalaryRatio){
            loanOneTotal += 1
            loanTwoTotal += 1
        }

        if(inputLoanOne.percentFinancing!! > inputLoanTwo.percentFinancing!!){
            loanOneTotal += 1
        }

        if(inputLoanOne.percentFinancing!! < inputLoanTwo.percentFinancing!!){
            loanTwoTotal += 1
        }

        if(inputLoanOne.percentFinancing == inputLoanTwo.percentFinancing){
            loanOneTotal += 1
            loanTwoTotal += 1
        }

        if(inputLoanOne.creditScore!! < inputLoanTwo.creditScore!!){
            loanOneTotal += 1
        }

        if(inputLoanOne.creditScore!! > inputLoanTwo.creditScore!!){
            loanTwoTotal += 1
        }

        if(inputLoanOne.creditScore == inputLoanTwo.creditScore){
            loanOneTotal += 1
            loanTwoTotal += 1
        }

        if(loanOneTotal == loanOneTotal){
            return randomValues[0]
        } else if(loanOneTotal > loanTwoTotal){
            return 0
        } else {
            return 1
        }







    }

}