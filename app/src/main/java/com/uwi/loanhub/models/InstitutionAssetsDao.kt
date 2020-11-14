package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InstitutionAssetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewAssets(inputAsset:InstitutionAssets)


    @Query("SELECT Institutions.name, Institutions.about, Institutions.website, Institutions.phone, Institutions.email, Institutions.logo, Institutions.slogan, Institutions.openingHours, InstitutionAssets.income, InstitutionAssets.revenue, InstitutionAssets.totalAssets, InstitutionAssets.year  FROM Institutions JOIN InstitutionAssets ON Institutions.name = InstitutionAssets.institutionName")
    fun getAllInstitution ():LiveData<List<InstitutionInstitutionAssets>>

    @Query("SELECT Institutions.name, Institutions.about, Institutions.website, Institutions.phone, Institutions.email, Institutions.logo, Institutions.slogan, Institutions.openingHours, InstitutionAssets.income, InstitutionAssets.revenue, InstitutionAssets.totalAssets, InstitutionAssets.year  FROM Institutions JOIN InstitutionAssets ON Institutions.name = InstitutionAssets.institutionName WHERE  InstitutionAssets.institutionName = :inputInstitution")
    fun getSpecificInstitution (inputInstitution:String):LiveData<List<InstitutionInstitutionAssets>>
}