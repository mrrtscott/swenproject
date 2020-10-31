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
                function.getCurrentDate()


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
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(jmmb)


            var ncb = Institution(
                "NCB",
                "Today, NCB stands as the largest financial group in Jamaica, focused on maintaining a profitable organization, which provides highly competitive and innovative products and service offerings for our customers. Its aim is to maintain a solid governance structure and robust compliance framework, while utilizing flexible business models and efficient operational processes and systems.",
                "Quality our Focus.. Serving our Pleasure",
                R.drawable.ncb,
                "info@ncb.com",
                "876-990-0905",
                "www.jncb.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()
            )

            institutionDao.addNewInstitution(ncb)





            var firstglobal = Institution(
                "First Global",
                "First Global Bank (Jamaica) Limited (FGB) is a comprehensive provider of first-rate commercial banking products and services registered to operate in Jamaica by the Bank of Jamaica. ",
                "Your commercial bank from Grace Kennedy",
                R.drawable.firstglobal,
                "info@fgb.com",
                "876-990-1901",
                "www.firstglobal-bank.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()
            )

            institutionDao.addNewInstitution(firstglobal)



            var jn = Institution(
                "JN Bank",
                "JN Bank is Jamaica’s first mutually-owned commercial bank, 100 per cent held by its savers and borrowers. Invigorated with the values and principles of its predecessor, the Jamaica National Building Society, JN Bank is underpinned by nearly a century and a half of rich history and legacy, which it continues to build on as a member of The Jamaica National Group.",
                "We'll help you find your way",
                R.drawable.jn,
                "info@jnbank.com",
                "876-902-7904",
                "www.jnbank.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(jn)


            var victoriamutal = Institution(
                "Victoria Mutual",
                "Built on a solid foundation of 130 years, Victoria Mutual Building Society (VMBS) is one of the leading financial institutions in the Caribbean. The organisation has provided financial solutions for generations of Jamaicans and Caribbean Nationals at home and in the Diaspora, with the core philosophy of creating value for members.",
                "Together We Can Build a Better Society",
                R.drawable.victoriamutual,
                "info@victoriamutual.com",
                "876-990-0909",
                "www.vmbs.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()
            )

            institutionDao.addNewInstitution(victoriamutal)


            var cibc = Institution(
                "CIBC",
                "CIBC FirstCaribbean is a relationship bank offering a full range of market leading financial services through our Corporate and Investment Banking, Retail and Business Banking and Wealth Management segments.",
                "",
                R.drawable.cibc,
                "info@cibc.com",
                "876-990-0909",
                "www.cibcfcib.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(cibc)


            var scotiabank = Institution(
                "Scotiabank",
                "Scotiabank is the leading bank in the Caribbean and Central America, with operations in 25 countries, including affiliates. We are the only Canadian bank with operations in four of the seven Central American countries, namely Costa Rica, Belize and Panama.",
                "Discover what's possible",
                R.drawable.scotiabank,
                "info@scotiabank.com",
                "876-997-7909",
                "wwww.jm.scotiabank.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(scotiabank)

        }
    }


}




