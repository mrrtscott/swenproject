package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanInstitutionViewModel (application: Application): AndroidViewModel(application) {


    private val repository:LoanInstitutionRepository
    val allLoanInstitution: LiveData<List<LoanInstitution>>
    val loansSpecificToUser = MutableLiveData<List<LoanInstitution>>()


    init{

        val loanInstitutionDao:LoanInstitutionDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanInstitutionDao()
        repository = LoanInstitutionRepository(loanInstitutionDao)
        allLoanInstitution =  repository.allLoanInstitution


    }


    fun getLoanInstitutionUserSpecific (inputSex:String, inputCreditScore:Int, inputLoanAmount: Int) = viewModelScope.launch(
        Dispatchers.IO) {
        val list = repository.getLoanInstitutionUserSpecific(inputSex,  inputCreditScore, inputLoanAmount)
        loansSpecificToUser.postValue(list)


    }
}