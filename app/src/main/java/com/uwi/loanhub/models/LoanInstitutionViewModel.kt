package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanInstitutionViewModel (application: Application, inputLoanID:String = ""): AndroidViewModel(application) {


    private val repository:LoanInstitutionRepository
    private val specificRepositoryForLoanDetails:LoanInstitutionRepository
    val allLoanInstitution: LiveData<List<LoanInstitution>>
    val loansSpecificToUser = MutableLiveData<List<LoanInstitution>>()
    val specificLoanDetail: LiveData<List<LoanInstitution>>


    init{

        val loanInstitutionDao:LoanInstitutionDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanInstitutionDao()
        repository = LoanInstitutionRepository(loanInstitutionDao,"")
        allLoanInstitution =  repository.allLoanInstitution

        specificRepositoryForLoanDetails  = LoanInstitutionRepository(loanInstitutionDao, inputLoanID)
        specificLoanDetail = specificRepositoryForLoanDetails.specificLoanInstitution

    }

    constructor()


    fun getLoanInstitutionUserSpecific (inputSex:String, inputCreditScore:Int, inputLoanAmount: Int) = viewModelScope.launch(
        Dispatchers.IO) {
        val list = repository.getLoanInstitutionUserSpecific(inputSex,  inputCreditScore, inputLoanAmount)
        loansSpecificToUser.postValue(list)


    }
}