package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import java.util.*
import kotlin.collections.ArrayList

class LoanRequirementViewModel (application: Application): AndroidViewModel(application) {

    private val repository:LoanRequirementRepository
    val allLoanRequirement: LiveData<List<LoanRequirement>>
    private var inputArrayList:ArrayList<String> =  arrayListOf("", "")


    init {
        val LoanRequirementDao = LoanHubDatabase.getDatabase(application, viewModelScope).LoanRequirementDao()
        repository = LoanRequirementRepository(LoanRequirementDao,inputArrayList )
        allLoanRequirement = repository.allLoanRequirement
    }



    fun setArray(inputArray:ArrayList<String>){

        inputArrayList = inputArray

    }
}