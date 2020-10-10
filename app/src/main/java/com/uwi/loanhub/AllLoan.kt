package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log

class AllLoan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_loan)


        val loanList = InstConstants.getLoans()
        Log.i("Loan Size", "${loanList.size}")
    }
}