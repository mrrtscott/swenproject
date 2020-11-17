package com.uwi.loanhub.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GlossaryViewModel (application: Application): AndroidViewModel(application) {

    var repository:GlossaryRepository
    var receivedArrayList: ArrayList<String> = arrayListOf(" ")
    var glossaryDao:GlossaryDao
    var glossaryDefinition:LiveData<List<Glossary>>

    init {

        glossaryDao = LoanHubDatabase.getDatabase(application, viewModelScope).GlossaryDao()
        repository = GlossaryRepository(glossaryDao, receivedArrayList)
        glossaryDefinition = repository.allDefinition

    }

    fun addGlossaryItem (inputGlossary: Glossary) = viewModelScope.launch(Dispatchers.IO){

        repository.addNewGlossary(inputGlossary)

    }

    fun setArray(inputArrayList:ArrayList<String>){

        receivedArrayList = inputArrayList
        repository = GlossaryRepository(glossaryDao, receivedArrayList)
        glossaryDefinition = repository.allDefinition

    }
}