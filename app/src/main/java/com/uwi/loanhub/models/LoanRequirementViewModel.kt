package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class LoanRequirementViewModel (application: Application): AndroidViewModel(application) {

    private var repository:LoanRequirementRepository
    var LoanRequirementDao:LoanRequirementDao
    var allLoanRequirement: LiveData<List<LoanRequirement>>
    private var inputArrayList:ArrayList<String> =  arrayListOf("1")



    init {
        LoanRequirementDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanRequirementDao()
        repository = LoanRequirementRepository(LoanRequirementDao,inputArrayList)
        allLoanRequirement = repository.allLoanRequirement

    }



    fun setArray(inputArray:ArrayList<String>){

        inputArrayList = inputArray
        repository = LoanRequirementRepository(LoanRequirementDao,inputArrayList)
        allLoanRequirement = repository.allLoanRequirement

    }

    fun insertLoanRequirement(inputLoanRequirement: LoanRequirement) = viewModelScope.launch(
        Dispatchers.IO){

        repository.addNewLoanRequirement(inputLoanRequirement)

    }
}