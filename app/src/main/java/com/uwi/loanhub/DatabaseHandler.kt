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

        private const val KEY_ID = "id"
        private const val KEY_CREATED_AT = "created_at"






    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}