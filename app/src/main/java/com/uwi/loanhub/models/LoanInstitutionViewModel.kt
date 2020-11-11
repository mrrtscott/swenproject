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

    private var receiveArrayList:ArrayList<String> = arrayListOf("0", "")
    var loanInstitutionLikesRating: LiveData<List<LoanInstitution>>


    init{

        loanInstitutionDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanInstitutionDao()
        repository = LoanInstitutionRepository(loanInstitutionDao,receiveArrayList)
        allLoanInstitution =  repository.allLoanInstitution

        specificRepositoryForLoanDetails  = LoanInstitutionRepository(loanInstitutionDao, receiveArrayList)
        specificLoanDetail = specificRepositoryForLoanDetails.specificLoanInstitution

        loanInstitutionLikesRating = repository.loanInstitutionLikesRating



    }




    fun getLoanInstitutionUserSpecific (inputSex:String, inputCreditScore:Int, inputLoanAmount: Int) = viewModelScope.launch(
        Dispatchers.IO) {
        val list = repository.getLoanInstitutionUserSpecific(inputSex,  inputCreditScore, inputLoanAmount)
        loansSpecificToUser.postValue(list)


    }




    fun setArray(input:ArrayList<String>){
        receiveArrayList = input

        specificRepositoryForLoanDetails  = LoanInstitutionRepository(loanInstitutionDao, receiveArrayList)
        specificLoanDetail = specificRepositoryForLoanDetails.specificLoanInstitution
        loanInstitutionLikesRating = specificRepositoryForLoanDetails.loanInstitutionLikesRating
    }
}