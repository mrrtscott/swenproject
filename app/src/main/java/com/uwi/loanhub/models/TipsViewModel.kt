package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 *
 */
class TipsViewModel(application: Application): AndroidViewModel(application) {

    private var repository:TipsRepository
    private var tipsLoanDao:TipsDao
    val allTips: LiveData<List<Tips>>

    init {
        tipsLoanDao = LoanHubDatabase.getDatabase(application, viewModelScope).TipsDao()
        repository = TipsRepository(tipsLoanDao)
        allTips = repository.allTips

    }


    /**
     * A method to add a tip to the database
     * @param inputTips A tip
     */
    fun addTips(inputTips: Tips) = viewModelScope.launch(Dispatchers.IO){
        repository.addTip(inputTips)
    }
}