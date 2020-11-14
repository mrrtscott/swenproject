package com.uwi.loanhub.models

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.uwi.loanhub.Functions
import com.uwi.loanhub.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(User::class, Loan::class, Institution::class, Branch::class, LoanRequirement::class, LoanLikes::class, LoanRating::class, InstitutionAssets::class), version = 10, exportSchema = true)
abstract class LoanHubDatabase : RoomDatabase() {


    abstract fun UserDao(): UserDao
    abstract fun LoanDao(): LoanDao
    abstract fun InstitutionDao(): InstitutionDao
    abstract fun LoanInstitutionDao (): LoanInstitutionDao
    abstract fun LoanRequirementDao():LoanRequirementDao
    abstract fun LoanLikesDao(): LoanLikesDao
    abstract fun LoanRatingDao(): LoanRatingDao
    abstract fun InstitutionAssetsDao(): InstitutionAssetsDao


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
                    populateDatabase(database.LoanDao(), database.InstitutionDao(), database.LoanRequirementDao(), database.InstitutionAssetsDao())

                }
            }

        }

        suspend fun populateDatabase(
            loansDao: LoanDao,
            institutionDao: InstitutionDao,
            loanRequirementDao: LoanRequirementDao,
            institutionAssetsDao: InstitutionAssetsDao
        ) {
            // Delete all content here.
            var function: Functions = Functions()

            //loansDao.deleteAllLoans()
            //institutionDao.deleteAllInstitutions()


            // Preload loans
            var loan_0 = Loan(
                0,
                "NCB",
                "Empire Loan",
                60000,
                1.5,
                "70 months",
                7.0,
                80,
                "Thinking of buying your own car? A National Bank car loan can be tailored to almost any budget—choose the payment options that are best for you. You can request a National Bank car loan at most dealerships.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_0)

            var loan_1 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Loanara",
                90000,
                10.5,
                "80 months",
                8.0,
                50,
                "Get 100% financing on new and used vehicles 2013 and newer. Sign Up For Newsletter. Apply Online.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_1)

            var loan_2 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Zoom Loan",
                75000,
                10.5,
                "80 months",
                8.0,
                90,
                "Get a personal loan with no hidden fees, no prepayment penalties, and no origination fees from one of the most trusted loan companies in the market.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_2)

            var loan_3 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Scoot Loan",
                80000,
                10.5,
                "80 months",
                8.0,
                90,
                "Fast Application, Competitive Rates And Quick Decisions. Apply for a new or used car loan or refinance your existing auto loan your bank.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_3)


            var loan_4 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Turbo Loan",
                20000,
                10.5,
                "80 months",
                8.0,
                90,
                "Get 100% financing on new and used vehicles 2013 and newer.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_4)



            var loan_5 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Smart Loan",
                25000,
                10.5,
                "80 months",
                8.0,
                90,
                "Apply online for a new or used car loan from this bank. Get approved for a financing based on your needs and within your budget.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_5)


            var loan_6 = Loan(
                0,
            function.givenList_shouldReturnARandomElement(),
            "Drive Loan",
            28000,
            10.5,
            "80 months",
            8.0,
            90,
            "Car Loans. Get your rates quickly on new, used and refinanced loans.",
            "Male",
            "Active",
            "2020-09-01"
            )
            loansDao.addNewLoan(loan_6)



            var loan_7 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Quick Loan",
                190000,
                10.5,
                "Get terms up to 84 months and no payments for 60 days.",
                8.0,
                90,
                "This is a CIBC loan description",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_7)

            var loan_8 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Infinite Loan",
                380000,
                10.5,
                "80 months",
                8.0,
                90,
                "We don't hide any fees, so you know exactly how much you'll pay.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_8)

            var loan_9 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Swift Loan",
                980000,
                10.5,
                "80 months",
                8.0,
                90,
                "We never penalize you for paying off your loan early.",
                "Female",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_9)

            var loan_10 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Anchor Loan",
                790000,
                10.5,
                "80 months",
                8.0,
                90,
                "Our simple application only takes a few minutes.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_10)

            var loan_11 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Ace Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "Refinance your car loan with this bank and lower monthly payments.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_11)

            var loan_12 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "King Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "Our low rates on auto loans make us one of the top auto lenders in Jamaica for new and used vehicles, auto refinance loans, lease buyouts, RV loans.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_12)

            var loan_13 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Dash Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "Whether you're buying, refinancing or looking to turn your lease into a loan, this bank offers a solution for you. Buy the car you want with confidence.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_13)

            var loan_14 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Easy Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "Be more confident when buying your next car or vehicle. Find an auto loan with our bank that fits your budget. See all financing options & apply now.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_14)

            var loan_15= Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Hustle Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_15)

            var loan_16 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Atlas Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_16)

            var loan_17 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Cornerstone Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_17)

            var loan_18 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Top Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_18)

            var loan_19 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Brisk Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_19)

            var loan_20 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Pro Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_20)

            var loan_21 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Express Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_21)

            var loan_22 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Optimum Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_22)

            var loan_23 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Elite Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_23)

            var loan_24 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Dart Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_24)

            var loan_25 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Path Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_25)

            var loan_26 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Sturdy Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_26)

            var loan_27 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Bound Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_27)

            var loan_28 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Crypto Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_28)

            var loan_29 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "GoLoan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_29)

            var loan_30 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Post Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_30)

            var loan_31 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Loanica",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_31)

            var loan_32 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Loanus",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_32)

            var loan_33 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Real Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_33)

            var loan_34 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Loanzio",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_34)

            var loan_35 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "mloan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_35)

            var loan_36 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Loanvoyage",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_36)

            var loan_37 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Bux Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_37)

            var loan_38 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Lotus Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_38)

            var loan_39 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Angel Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_39)

            var loan_40 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Stack Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_40)

            var loan_41 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Covered Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_41)

            var loan_42 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Bite Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_42)

            var loan_43 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Sact Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_43)

            var loan_44 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Butt Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_1)

            var loan_45 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Helix Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_45)

            var loan_46 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Plow Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_46)

            var loan_47 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Thirs Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_47)

            var loan_48 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Billzo Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_48)

            var loan_49 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Pier Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_49)

            var loan_50 = Loan(
                0,
                function.givenList_shouldReturnARandomElement(),
                "Wallet Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "This is a CIBC loan description",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_50)























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
                function.givenList_shouldReturnARandomElement(),
                "CIBC FirstCaribbean is a relationship bank offering a full range of market leading financial services through our Corporate and Investment Banking, Retail and Business Banking and Wealth Management segments.",
                "For what matters",
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


            var scotiabankLR = LoanRequirement ("Scotiabank","Two photo identification", "Employed for over 6 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 1 year")
            var ncbLR = LoanRequirement ("NCB","Three photo identification", "Employed for over 8 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 2 year")
            var cibcLR = LoanRequirement ("CIBC","Two photo identification", "Employed for over 7 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 3 year")
            var victoriamutalLR = LoanRequirement ("Victoria Mutual","Two photo identification", "Employed for over 5 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 1 year")
            var firstglobalLR = LoanRequirement ("First Global","Two photo identification", "Employed for over 3 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 2 year")
            var jnLR = LoanRequirement ("JN Bank","Three photo identification", "Employed for over 9 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 3 year")
            var jmmbLR = LoanRequirement ("JMMB","Three photo identification", "Employed for over 9 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 2 year")
            var sagicorLR = LoanRequirement ("Sagicor","Two photo identification", "Employed for over 12 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 1 year")


            loanRequirementDao.addLoanRequirement(scotiabankLR)
            loanRequirementDao.addLoanRequirement(ncbLR)
            loanRequirementDao.addLoanRequirement(cibcLR)
            loanRequirementDao.addLoanRequirement(victoriamutalLR)
            loanRequirementDao.addLoanRequirement(firstglobalLR)
            loanRequirementDao.addLoanRequirement(jnLR)
            loanRequirementDao.addLoanRequirement(jmmbLR)
            loanRequirementDao.addLoanRequirement(sagicorLR)



            //Assets are being added

            var scotiabankAssets2017 = InstitutionAssets("Scotiabank", 2017, 39.42,  12.40, 490.88)
            institutionAssetsDao.addNewAssets(scotiabankAssets2017)

            var scotiabankAssets2018 = InstitutionAssets("Scotiabank", 2018, 31.42,  17.40, 498.88)
            institutionAssetsDao.addNewAssets(scotiabankAssets2018)



        }
    }


}




