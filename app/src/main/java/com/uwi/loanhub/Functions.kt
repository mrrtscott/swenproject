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

/**
 * This class is stores a number of method which provide universal services to other classes
 */
class Functions {

    /**
     * This method is used to encrypt a string which has been passed to it
     * @param inputPassword A raw string which has characters that are visible
     * @return An encrypted string
     */
    fun encryptSys(inputPassword: String): String {

        var md: MessageDigest = MessageDigest.getInstance("SHA-512")
        var digest = md.digest(inputPassword.toByteArray())
        var sb: StringBuilder = StringBuilder()
        if(inputPassword.isNotEmpty()){
            var i = 0
            while (i < digest.count()) {
                sb.append(((digest[i] and 0xff.toByte()) + 0x100).toString(16).substring(0, 1))
                i++
            }
        }

        return sb.toString()


    }

    /**
     * A that generates the current date and time
     * @return A formatted date which is of type string
     */
    fun getCurrentDate(): String{

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted

    }

    /**
     * This function is used to generate a random bank name stored in a list
     * @return The name of the randomly selected bank is returned as a String
     */
    fun givenList_shouldReturnARandomElement():String  {
        val givenList = Arrays.asList("NCB", "First Global","Scotiabank", "Victoria Mutual", "JN Bank", "JMMB", "Sagicor", "CIBC")
        val rand = Random()
        val randomElement = givenList[rand.nextInt(givenList.size)]
        return randomElement
    }

    /**
     * A simple function that formats dollars.
     * @param num A figure of type Double which is to to be formatted.
     * @return A formatted date which is of type String
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun currencyFormatter(num: String): String? {
        val m = num.toDouble()
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(m)
    }


    /**
     * An simple algorithm which will calculate which loan to recommend
     * @param inputLoanOne Referred to as Loan One and is the first loan on which operation can be performed. It is of type LoanInstitution
     * @param inputLoanTwo Referred to as Loan Two and is the second loan on which operation can be performed. It is of type LoanInstitution
     * @param inputUser An inout of type User from which critical details will be extracted for the algorithm to work
     * @return The function returns which of the two loans is being recommended of type integer. Zero (0) represents the first loan and one (1) the second
     */
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

        if((inputLoanOne.interestRate?.times(2))!! < inputLoanTwo.interestRate!!){
            loanOneTotal += 1
        }


        if((inputLoanTwo.interestRate?.times(2))!! < inputLoanOne.interestRate!!){
            loanTwoTotal += 1
        }



        loanOneTotal += (inputLoanOne.description!!.length/1000)
        loanTwoTotal += (inputLoanTwo.description!!.length/1000)

        loanOneTotal += (inputLoanOne.about!!.length/1000)
        loanTwoTotal += (inputLoanTwo.about!!.length/1000)

        return if(loanOneTotal > loanTwoTotal){
            0
        } else {
            1
        }







    }


}