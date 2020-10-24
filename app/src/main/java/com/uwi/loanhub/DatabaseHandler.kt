package com.uwi.loanhub

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context): SQLiteOpenHelper  (context, DATABASE_NAME, null, DATABASE_VERSION){


    companion object{

        private const val DATABASE_VERSION = 1

        //DATABASE NAME
        private const val DATABASE_NAME = "LoanDatabase"

        //TABLE NAMES
        private const val TABLE_USERS = "UsersTable"
        private const val TABLE_LOANS = "LoansTable"
        private const val TABLE_INSTITUTIONS = "InstitutionsTable"
        private const val TABLE_PARISH = "ParishTable"
        private const val TABLE_CITY = "CityTable"

        //COMMON COLUMNS

        private const val KEY_ID = "_id"
        private const val KEY_CREATED_AT = "created_at"


        //USER TABLE
        private const val USER_FIRST_NAME = "firstName"
        private const val USER_LAST_NAME =  "lastName"
        private const val USER_EMAIL = "email"
        private const val USER_USERNAME = "username"
        private const val USER_PASSWORD = "password"
        private const val USER_SEX = "sex"
        private const val USER_DOB = "dob"
        private const val USER_SALARY = "salary"
        private const val USER_CITY = "city"
        private const val USER_PARISH = "parish"
        private const val USER_PRIMARY_BANK = "primaryBank"
        private const val USER_LOAN_TYPE = "loanType"
        private const val USER_LOAN_AMOUNT = "loanAmount"
        private const val USER_OCCUPATION = "occupation"

        private const val CREATE_TABLE_USER = "CREATE TABLE" + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + USER_FIRST_NAME + " TEXT, " + USER_LAST_NAME + " TEXT, " + USER_EMAIL + " TEXT, " + USER_USERNAME + " TEXT, " + USER_PASSWORD + " TEXT, " + USER_SEX + " TEXT," + USER_DOB + " TEXT," + USER_SALARY + " TEXT," + USER_CITY + " TEXT," + USER_PARISH + " TEXT," + USER_PRIMARY_BANK + " TEXT," + USER_LOAN_TYPE + " TEXT," + USER_LOAN_AMOUNT + " REAL," + USER_OCCUPATION + " TEXT," + KEY_CREATED_AT + " DATETIME" + ")"


        //LOAN TABLES

        private const val LOAN_INSTITUTION = "institution"
        private const val LOAN_NAME = "loanName"
        private const val LOAN_AMOUNT = "loanAmount"
        private const val LOAN_INTEREST_RATE = "loanInterestRate"
        private const val LOAN_TERMS_REPAY = "loanTermsRepay"
        private const val LOAN_PERCENT_FINANCING = "loanPercentFinancing"
        private const val LOAN_CREDIT_SCORE = "percentCreditScore"
        private const val LOAN_DESCRIPTION = "loanDescription"
        private const val LOAN_STATUS = "loanStatus"

        private const val CREATE_TABLE_LOANS = "CREATE TABLE" + TABLE_LOANS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + LOAN_INSTITUTION + " TEXT, " + LOAN_NAME + " TEXT, " + LOAN_AMOUNT + " REAL, " + LOAN_INTEREST_RATE + " REAL, " + LOAN_TERMS_REPAY + " INTEGER, " + LOAN_PERCENT_FINANCING + " REAL," + LOAN_CREDIT_SCORE + " INTEGER," + LOAN_DESCRIPTION + " TEXT," + LOAN_STATUS + " TEXT," + KEY_CREATED_AT + " DATETIME" + ")"








    }

    override fun onCreate(db: SQLiteDatabase?) {

        if (db != null) {
            db.execSQL(CREATE_TABLE_USER)
            db.execSQL(CREATE_TABLE_LOANS)

        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}