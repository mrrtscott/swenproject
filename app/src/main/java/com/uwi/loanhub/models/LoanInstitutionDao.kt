package com.uwi.loanhub.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface LoanInstitutionDao {


    @Query("SELECT Loans.id, Loans.institution, Loans.loanName, Loans.loanAmount,  Loans.interestRate, Loans.termsRepay, Loans.percentFinancing, Loans.creditScore, Loans.description, Loans.target, Loans.status, Institutions.name, Institutions.about, Institutions.slogan, Institutions.logo, Institutions.email,  Institutions.phone, Institutions.website, Institutions.openingHours, Institutions.institutionStatus  FROM Loans JOIN Institutions WHERE Loans.institution = Institutions.name")
    fun getLoanInstitution(): LiveData<List<LoanInstitution>>

    @Query("SELECT Loans.id, Loans.institution, Loans.loanName, Loans.loanAmount,  Loans.interestRate, Loans.termsRepay, Loans.percentFinancing, Loans.creditScore, Loans.description, Loans.target, Loans.status, Institutions.name, Institutions.about, Institutions.slogan, Institutions.logo, Institutions.email,  Institutions.phone, Institutions.website, Institutions.openingHours, Institutions.institutionStatus  FROM Loans JOIN Institutions WHERE Loans.institution = Institutions.name AND Loans.loanAmount >= :inputLoanAmount AND :inputCreditScore >= Loans.creditScore AND  (:inputSex LIKE '%' ||  Loans.target OR Loans.target = 'Everybody')  AND Loans.status = 'Active'")
    fun getLoanInstitutionUserSpecific(inputSex:String , inputCreditScore:Int, inputLoanAmount: Int ): List<LoanInstitution>


    @Query("SELECT Loans.id, Loans.institution, Loans.loanName, Loans.loanAmount,  Loans.interestRate, Loans.termsRepay, Loans.percentFinancing, Loans.creditScore, Loans.description, Loans.target, Loans.status, Institutions.name, Institutions.about, Institutions.slogan, Institutions.logo, Institutions.email,  Institutions.phone, Institutions.website, Institutions.openingHours, Institutions.institutionStatus  FROM Loans JOIN Institutions WHERE Loans.institution = Institutions.name AND Loans.loanName=:inputLoanName")
    fun getSpecificLoan (inputLoanName: String):LiveData<List<LoanInstitution>>











}