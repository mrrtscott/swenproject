package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InstitutionAssetsViewModel (application: Application): AndroidViewModel(application) {

    var repository:InstitutionAssetsRepository
    var receivedArrayList:ArrayList<String> = arrayListOf(" ")
    var InstitutionAssetsDao:InstitutionAssetsDao
    var specificInstitutionInstitutionAssets:LiveData<List<InstitutionInstitutionAssets>>


    init{

        InstitutionAssetsDao = LoanHubDatabase.getDatabase(application, viewModelScope).InstitutionAssetsDao()
        repository = InstitutionAssetsRepository(InstitutionAssetsDao, receivedArrayList)
        specificInstitutionInstitutionAssets = repository.specificInstitutionAssets

    }


    fun addNewInstitutionAssets(inputInstitutionAssets: InstitutionAssets) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNewInstitutionAssets(inputInstitutionAssets)
    }

    fun setArray(inputArrayList:ArrayList<String>){

        receivedArrayList = inputArrayList
        repository = InstitutionAssetsRepository(InstitutionAssetsDao, receivedArrayList)
        specificInstitutionInstitutionAssets = repository.specificInstitutionAssets

        println(receivedArrayList[0])



    }
}