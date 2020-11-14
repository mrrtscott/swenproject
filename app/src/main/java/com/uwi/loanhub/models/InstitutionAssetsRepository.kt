package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class InstitutionAssetsRepository (private val inputInstitutionAssetsDao: InstitutionAssetsDao, inputArrayList:ArrayList<String>) {


    var inputInstitution:String = inputArrayList[0]

    val specificInstitutionAssets:LiveData<List<InstitutionInstitutionAssets>> = inputInstitutionAssetsDao.getSpecificInstitution( inputInstitution)

    suspend fun addNewInstitutionAssets(inputInstitutionAssets: InstitutionAssets){

        inputInstitutionAssetsDao.addNewAssets(inputInstitutionAssets)

    }


}