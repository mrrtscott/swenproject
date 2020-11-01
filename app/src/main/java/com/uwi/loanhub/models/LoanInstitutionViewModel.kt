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


    fun getLoanInstitutionUserSpecific (inputSex:String, inputSalary:Double, inputCreditScore:Int, inputOccupation:String, inputPrimaryBank:String, inputLoanType:String, inputLoanAmount: Int, inputTarget:String) = viewModelScope.launch(
        Dispatchers.IO) {
        val list = repository.getLoanInstitutionUserSpecific(inputSex, inputSalary, inputCreditScore, inputOccupation, inputPrimaryBank, inputLoanType, inputLoanAmount, inputTarget)
        loansSpecificToUser.postValue(list)


    }
}