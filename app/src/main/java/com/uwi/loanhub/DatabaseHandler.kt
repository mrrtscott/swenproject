package com.uwi.loanhub

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context): SQLiteOpenHelper  (context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "LoanDatabase"
        private const val TABLE_USERS = "UsersTable"
        private const val TABLE_LOANS = "LoansTable"


    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}