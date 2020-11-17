package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class GlossaryRepository(private val inputGlossaryDao: GlossaryDao, inputArrayList: ArrayList<String>) {

    val searchName = inputArrayList[0]
    val allDefinition:LiveData<List<Glossary>> = inputGlossaryDao.getDefinition(searchName)

    suspend fun addNewGlossary(inputGlossary: Glossary){

        inputGlossaryDao.insert(inputGlossary)

    }


}