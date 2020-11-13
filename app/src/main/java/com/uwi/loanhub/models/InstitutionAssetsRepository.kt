package com.uwi.loanhub.models

class InstitutionAssetsRepository (private val inputInstitutionAssetsDao: InstitutionAssetsDao) {

    suspend fun addNewInstitutionAssets(inputInstitutionAssets: InstitutionAssets){

        inputInstitutionAssetsDao.addNewAssets(inputInstitutionAssets)

    }


}