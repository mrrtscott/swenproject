package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

class LoanInstitutionViewModel (application: Application): AndroidViewModel(application) {


    private val repository:LoanInstitutionRepository
    val allLoanInstitution: LiveData<List<LoanInstitution>>


    init{

        val loanInstitutionDao:LoanInstitutionDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanInstitutionDao()
        repository = LoanInstitutionRepository(loanInstitutionDao)
        allLoanInstitution =  repository.allLoanInstitution


    }
}