package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InstitutionAssetsViewModel (application: Application): AndroidViewModel(application) {

    val repository:InstitutionAssetsRepository


    init{

        val InstitutionAssetsDao = LoanHubDatabase.getDatabase(application, viewModelScope).InstitutionAssetsDao()
        repository = InstitutionAssetsRepository(InstitutionAssetsDao)

    }


    fun addNewInstitution(inputInstitutionAssets: InstitutionAssets) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNewInstitutionAssets(inputInstitutionAssets)
    }
}