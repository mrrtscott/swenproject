package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanViewModel (application: Application): AndroidViewModel(application) {

    private val repository:LoanRepository

    val allLoans:LiveData<List<Loan>>

    init{
        val LoanDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanDao()
        repository = LoanRepository(LoanDao)
        allLoans = repository.allLoans
    }

    fun insert(inputLoan: Loan) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNewLoan(inputLoan)
    }
}