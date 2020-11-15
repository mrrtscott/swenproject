package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BranchViewModel (application: Application): AndroidViewModel(application) {

    var repository:BranchRepository
    var BranchDao: BranchDao

    var bankBranches: LiveData<List<Branch>>
    var closeBranches: LiveData<List<Branch>>

    private var inputArrayList:ArrayList<String> =  arrayListOf(" ", " ", " ", " ")


    init {

        BranchDao = LoanHubDatabase.getDatabase(application, viewModelScope).BranchDao()
        repository = BranchRepository(BranchDao, inputArrayList)
        bankBranches = repository.bankBranches
        closeBranches = repository.closeBranches

    }


    fun insertBranch(inputBranch: Branch) = viewModelScope.launch(
        Dispatchers.IO){
        repository.addBranch(inputBranch)
    }

    fun setArray(inputList: ArrayList<String>){
        inputArrayList = inputList
        repository = BranchRepository(BranchDao, inputArrayList)
        bankBranches = repository.bankBranches
        closeBranches = repository.closeBranches
    }
}