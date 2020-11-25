package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InstitutionViewModel (application: Application): AndroidViewModel(application) {

    private val repository:InstitutionRepository

    val allInstitutions: LiveData<List<Institution>>
    val institutionList = MutableLiveData<List<Institution>>()

    init{
        val InstitutionDao = LoanHubDatabase.getDatabase(application, viewModelScope).InstitutionDao()
        repository = InstitutionRepository(InstitutionDao)
        allInstitutions= repository.allInstitutions

    }


    /**
     * A method used to add new institution to the database
     * @param inputInstitution input of type Institution
     */
    fun addNewInstitution(inputInstitution: Institution) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNewInstitution(inputInstitution)
    }

    /**
     * A method used to get a specific institution
     * @param inputName The name of the institution
     */
    fun getUSpecificInstitution(inputName: String) = viewModelScope.launch(Dispatchers.IO){
        val list = repository.getUSpecificInstitution(inputName)
        institutionList.postValue(list)

    }

}