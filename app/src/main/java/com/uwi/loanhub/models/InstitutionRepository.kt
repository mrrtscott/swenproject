package com.uwi.loanhub.models


import androidx.lifecycle.LiveData
class InstitutionRepository (private val inputInstitutionDao: InstitutionDao){

    val allInstitutions: LiveData<List<Institution>> = inputInstitutionDao.getAllInstitutions()

    suspend fun addNewInstitution(inputInstitution: Institution){
        inputInstitutionDao.addNewInstitution(inputInstitution)

    }

    fun getUSpecificInstitution(inputName: String): List<Institution>{
        return inputInstitutionDao.getUSpecificInstitution(inputName)
    }






}