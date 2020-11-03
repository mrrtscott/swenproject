package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanInstitutionViewModel (application: Application): AndroidViewModel(application) {


    private val repository:LoanInstitutionRepository
    private var specificRepositoryForLoanDetails:LoanInstitutionRepository
    val allLoanInstitution: LiveData<List<LoanInstitution>>
    val loansSpecificToUser = MutableLiveData<List<LoanInstitution>>()
    var specificLoanDetail: LiveData<List<LoanInstitution>>

    val loanInstitutionDao:LoanInstitutionDao

    private var inputLoanID:String= ""


    init{

        loanInstitutionDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanInstitutionDao()
        repository = LoanInstitutionRepository(loanInstitutionDao,"230")
        allLoanInstitution =  repository.allLoanInstitution

        specificRepositoryForLoanDetails  = LoanInstitutionRepository(loanInstitutionDao, inputLoanID)
        specificLoanDetail = specificRepositoryForLoanDetails.specificLoanInstitution

    }




    fun getLoanInstitutionUserSpecific (inputSex:String, inputCreditScore:Int, inputLoanAmount: Int) = viewModelScope.launch(
        Dispatchers.IO) {
        val list = repository.getLoanInstitutionUserSpecific(inputSex,  inputCreditScore, inputLoanAmount)
        loansSpecificToUser.postValue(list)


    }

    fun setLoanID(input:String){
        inputLoanID = input
        println("The input ".plus(input))

        println("Actual input".plus(inputLoanID))

        specificRepositoryForLoanDetails  = LoanInstitutionRepository(loanInstitutionDao, inputLoanID)
        specificLoanDetail = specificRepositoryForLoanDetails.specificLoanInstitution
    }
}