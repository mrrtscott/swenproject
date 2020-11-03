package com.uwi.loanhub

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.MessageDigest
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.experimental.and

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

}