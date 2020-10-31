package com.uwi.loanhub.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.uwi.loanhub.Functions
import com.uwi.loanhub.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(User::class, Loan::class, Institution::class, Branch::class), version = 3, exportSchema = false)
abstract class LoanHubDatabase : RoomDatabase() {


    abstract fun UserDao(): UserDao
    abstract fun LoanDao(): LoanDao
    abstract fun InstitutionDao(): InstitutionDao


    companion object {

        @Volatile
        private var INSTANCE: LoanHubDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LoanHubDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoanHubDatabase::class.java,
                    "LoanHub_Database"
                ).addCallback(UserDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }


        }

    }


    private class UserDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.LoanDao(), database.InstitutionDao())
                }
            }

        }

        suspend fun populateDatabase(loansDao: LoanDao, institutionDao: InstitutionDao) {
            // Delete all content here.
            var function: Functions = Functions()

            loansDao.deleteAllLoans()

            institutionDao.deleteAllInstitutions()


            // Preload loans
            var loan_0 = Loan(
                0,
                "NCB",
                "NCB Loan",
                60000,
                9.5,
                "70 months",
                7.0,
                80,
                "This is a NCB loan description",
                "Everyone",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_0)

            var loan_1 = Loan(
                0,
                "CIBC",
                "CIBC Loan",
                70000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Everyone",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_1)

            var sagicor = Institution(
                "Sagicor",
                "Sagicor Group Jamaica is a majority owned subsidiary of the Sagicor Financial Corporation (SFC). The history of Sagicor dates back to 1840, SFC is a dynamic, indigenous Group which has been redefining financial services in the Caribbean, building a strong base from which it has expanded into the international financial services market. Sagicor now operates in 22 countries in the Caribbean, Latin America, the United Kingdom and the United States.",
                "Wise Financial Thinking for Life",
                R.drawable.sagicormg,
                "info@sagicor.com",
                "876-990-0001",
                "www.sagicor.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate().toString()


            )

            institutionDao.addNewInstitution(sagicor)

            var jmmb = Institution(
                "JMMB",
                "JMMB is an integrated financial services provider who is committed to showing heart-to-heart connections and providing genuine, caring relationships, as we proactively deliver personalised financial plans that offer access to our wide range of financial solutions, including banking and investments.  We currently partner with 283,000 clients, and their families, and we also want to be your Financial Life Goals Partner, holding your hand and coaching you along the journey towards your goals.",
                "Our Promise to You",
                R.drawable.jmmb,
                "info@jmmb.com",
                "876-990-0909",
                "www.jmmb.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate().toString()
            )

            institutionDao.addNewInstitution(jmmb)
            /*
            )
            val ncb = Institution(
                "NCB",
                "info@ncb.com",
                "876-990-0909",
                arrayOf("Mandeville", "May Pen"),
                R.drawable.ncb
            )
            val firstglobal = com.uwi.loanhub.Institution(
                "First Global",
                "info@fgb.com",
                "876-990-0909",
                arrayOf("Mandeville", "May Pen"),
                R.drawable.firstglobal
            )
            val jn = com.uwi.loanhub.Institution(
                "JN Bank",
                "info@jnbank.com",
                "876-990-0909",
                arrayOf("Mandeville", "May Pen"),
                R.drawable.jn
            )
            val victoriamutal = com.uwi.loanhub.Institution(
                "Victoria Mutual",
                "info@victoriamutual.com",
                "876-990-0909",
                arrayOf("Mandeville", "May Pen"),
                R.drawable.victoriamutual
            )
            val cibc = com.uwi.loanhub.Institution(
                "CIBC",
                "info@cibc.com",
                "876-990-0909",
                arrayOf("Mandeville", "May Pen"),
                R.drawable.cibc
            )
            val scotiabank = com.uwi.loanhub.Institution(
                "Scotiabank",
                "info@scotiabank.com",
                "876-997-7909",
                arrayOf("Mandeville", "May Pen"),
                R.drawable.scotiabank
            ) */

        }
    }


}




