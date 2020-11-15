package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class BranchRepository  (private val inputBranchDao: BranchDao, inputArray:ArrayList<String>) {

    var bank:String = inputArray[0]
    var city:String = inputArray[1]
    var parish:String = inputArray[2]


    var bankBranches: LiveData<List<Branch>> = inputBranchDao.getBranchToBank(bank)
    var closeBranches: LiveData<List<Branch>> = inputBranchDao.getCloseBranch(bank, city, parish)


    suspend fun addBranch(inputBranch:Branch){
        inputBranchDao.addBranch(inputBranch)
    }


}