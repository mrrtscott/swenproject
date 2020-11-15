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


@Database(entities = arrayOf(User::class, Loan::class, Institution::class, LoanRequirement::class, LoanLikes::class, LoanRating::class, InstitutionAssets::class, Branch::class), version = 17, exportSchema = false)
abstract class LoanHubDatabase : RoomDatabase() {


    abstract fun UserDao(): UserDao
    abstract fun LoanDao(): LoanDao
    abstract fun InstitutionDao(): InstitutionDao
    abstract fun LoanInstitutionDao (): LoanInstitutionDao
    abstract fun LoanRequirementDao():LoanRequirementDao
    abstract fun LoanLikesDao(): LoanLikesDao
    abstract fun LoanRatingDao(): LoanRatingDao
    abstract fun InstitutionAssetsDao(): InstitutionAssetsDao
    abstract fun BranchDao():BranchDao


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
                    populateBranch(database.BranchDao())

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
            val function: Functions = Functions()

            //loansDao.deleteAllLoans()
            //institutionDao.deleteAllInstitutions()


            // Preload loans
            val loan_0 = Loan(
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

            val loan_1 = Loan(
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

            val loan_2 = Loan(
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

            val loan_3 = Loan(
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



            val loan_5 = Loan(
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

            val loan_38 = Loan(
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

            val loan_39 = Loan(
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

            val loan_40 = Loan(
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

            val loan_41 = Loan(
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

            val loan_42 = Loan(
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

            val loan_43 = Loan(
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

            val loan_44 = Loan(
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

            val loan_45 = Loan(
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

            val loan_46 = Loan(
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

            val loan_47 = Loan(
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

            val loan_48 = Loan(
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

            val loan_49 = Loan(
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

            val loan_50 = Loan(
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























            val sagicor = Institution(
                "Sagicor",
                "Sagicor Group Jamaica is a majority owned subsidiary of the Sagicor Financial Corporation (SFC). The history of Sagicor dates back to 1840, SFC is a dynamic, indigenous Group which has been redefining financial services in the Caribbean, building a strong base from which it has expanded into the international financial services market. Sagicor now operates in 22 countries in the Caribbean, Latin America, the United Kingdom and the United States.",
                "Wise Financial Thinking for Life",
                R.drawable.sagicormg,
                "info@sagicor.com",
                "876-990-0001",
                "https://sagicor.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()


            )

            institutionDao.addNewInstitution(sagicor)

            val jmmb = Institution(
                "JMMB",
                "JMMB is an integrated financial services provider who is committed to showing heart-to-heart connections and providing genuine, caring relationships, as we proactively deliver personalised financial plans that offer access to our wide range of financial solutions, including banking and investments.  We currently partner with 283,000 clients, and their families, and we also want to be your Financial Life Goals Partner, holding your hand and coaching you along the journey towards your goals.",
                "Our Promise to You",
                R.drawable.jmmb,
                "info@jmmb.com",
                "876-990-0909",
                "https://jmmb.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(jmmb)


            val ncb = Institution(
                "NCB",
                "Today, NCB stands as the largest financial group in Jamaica, focused on maintaining a profitable organization, which provides highly competitive and innovative products and service offerings for our customers. Its aim is to maintain a solid governance structure and robust compliance framework, while utilizing flexible business models and efficient operational processes and systems.",
                "Quality our Focus.. Serving our Pleasure",
                R.drawable.ncb,
                "info@ncb.com",
                "876-990-0905",
                "https://jncb.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()
            )

            institutionDao.addNewInstitution(ncb)





            val firstglobal = Institution(
                "First Global",
                "First Global Bank (Jamaica) Limited (FGB) is a comprehensive provider of first-rate commercial banking products and services registered to operate in Jamaica by the Bank of Jamaica. ",
                "Your commercial bank from Grace Kennedy",
                R.drawable.firstglobal,
                "info@fgb.com",
                "876-990-1901",
                "https://firstglobal-bank.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()
            )

            institutionDao.addNewInstitution(firstglobal)



            val jn = Institution(
                "JN Bank",
                "JN Bank is Jamaica’s first mutually-owned commercial bank, 100 per cent held by its savers and borrowers. Invigorated with the values and principles of its predecessor, the Jamaica National Building Society, JN Bank is underpinned by nearly a century and a half of rich history and legacy, which it continues to build on as a member of The Jamaica National Group.",
                "We'll help you find your way",
                R.drawable.jn,
                "info@jnbank.com",
                "876-902-7904",
                "https://jnbank.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(jn)


            val victoriamutal = Institution(
                "Victoria Mutual",
                "Built on a solid foundation of 130 years, Victoria Mutual Building Society (VMBS) is one of the leading financial institutions in the Caribbean. The organisation has provided financial solutions for generations of Jamaicans and Caribbean Nationals at home and in the Diaspora, with the core philosophy of creating value for members.",
                "Together We Can Build a Better Society",
                R.drawable.victoriamutual,
                "info@victoriamutual.com",
                "876-990-0909",
                "https://vmbs.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()
            )

            institutionDao.addNewInstitution(victoriamutal)


            val cibc = Institution(
                function.givenList_shouldReturnARandomElement(),
                "CIBC FirstCaribbean is a relationship bank offering a full range of market leading financial services through our Corporate and Investment Banking, Retail and Business Banking and Wealth Management segments.",
                "For what matters",
                R.drawable.cibc,
                "info@cibc.com",
                "876-990-0909",
                "https://cibcfcib.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(cibc)


            val scotiabank = Institution(
                "Scotiabank",
                "Scotiabank is the leading bank in the Caribbean and Central America, with operations in 25 countries, including affiliates. We are the only Canadian bank with operations in four of the seven Central American countries, namely Costa Rica, Belize and Panama.",
                "Discover what's possible",
                R.drawable.scotiabank,
                "info@scotiabank.com",
                "876-997-7909",
                "https://jm.scotiabank.com",
                "Monday - Friday 8:00 AM - 3:00 PM",
                "Active",
                function.getCurrentDate()

            )

            institutionDao.addNewInstitution(scotiabank)


            val scotiabankLR = LoanRequirement ("Scotiabank","Two photo identification", "Employed for over 6 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 1 year")
            val ncbLR = LoanRequirement ("NCB","Three photo identification", "Employed for over 8 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 2 year")
            val cibcLR = LoanRequirement ("CIBC","Two photo identification", "Employed for over 7 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 3 year")
            val victoriamutalLR = LoanRequirement ("Victoria Mutual","Two photo identification", "Employed for over 5 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 1 year")
            val firstglobalLR = LoanRequirement ("First Global","Two photo identification", "Employed for over 3 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 2 year")
            val jnLR = LoanRequirement ("JN Bank","Three photo identification", "Employed for over 9 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 3 year")
            val jmmbLR = LoanRequirement ("JMMB","Three photo identification", "Employed for over 9 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 2 year")
            val sagicorLR = LoanRequirement ("Sagicor","Two photo identification", "Employed for over 12 months. Applicant should produce three (3) playslips and a bank statement","Applicant should produce two character reference from two justices of peace", "Vehicle should at less than 1 year")


            loanRequirementDao.addLoanRequirement(scotiabankLR)
            loanRequirementDao.addLoanRequirement(ncbLR)
            loanRequirementDao.addLoanRequirement(cibcLR)
            loanRequirementDao.addLoanRequirement(victoriamutalLR)
            loanRequirementDao.addLoanRequirement(firstglobalLR)
            loanRequirementDao.addLoanRequirement(jnLR)
            loanRequirementDao.addLoanRequirement(jmmbLR)
            loanRequirementDao.addLoanRequirement(sagicorLR)



            //Assets are being added

            val scotiabankAssets2017 = InstitutionAssets("Scotiabank", 2017, 39.42,  12.40, 490.88)

            val scotiabankAssets2018 = InstitutionAssets("Scotiabank", 2018, 31.42,  17.40, 498.88)

            val ncbAssets2017 = InstitutionAssets("NCB", 2017, 61.56, 16.5, 718.81)

            val cibcAssets2017 = InstitutionAssets("CIBC", 2017, 79.36,2.05, 1076.50  )

            val victoriamutalAssets2017 =InstitutionAssets("Victoria Mutual", 2017, 6.53, 4.01, 115.6 )

            val firstglobal2017 = InstitutionAssets("First Global", 2017, 4.53, 0.468, 54.49)

            val jn2017 = InstitutionAssets("JN Bank", 2017, 11.64, 1.87, 180.66)

            val jmmb2017 =  InstitutionAssets("JMMB", 2017, 14.65, 3.35, 251.56)

            val sagicor2017 = InstitutionAssets("Sagicor", 2017, 10.85, 1.78, 125.25)



            institutionAssetsDao.addNewAssets(scotiabankAssets2017)
            institutionAssetsDao.addNewAssets(scotiabankAssets2018)
            institutionAssetsDao.addNewAssets(ncbAssets2017)
            institutionAssetsDao.addNewAssets(cibcAssets2017)
            institutionAssetsDao.addNewAssets(victoriamutalAssets2017)
            institutionAssetsDao.addNewAssets(firstglobal2017)
            institutionAssetsDao.addNewAssets(jn2017)
            institutionAssetsDao.addNewAssets(jmmb2017)
            institutionAssetsDao.addNewAssets(sagicor2017)











        }


        suspend fun populateBranch(branchDao: BranchDao) {

            val scotiabankSavannaLaMar = Branch("Savanna La Mar", "Scotiabank", "19 Great George's Street","Savanna La Mar", "Westmoreland", "Negril", 0.00, 0.00,"Caswell Dawes", "Active")

            val scotiabankFalmouth= Branch("Falmouth", "Scotiabank", "Trewlany Wharf","Falmouth", "Trelawny", "", 0.00, 0.00,"Robert Wright", "Active")
            val scotiabankMontegoBay = Branch("Montego Bay", "Scotiabank", "6-7 Sam Sharpe Square","Montego Bay", "St James", "", 0.00, 0.00,"Rayon Clarke", "Active")
            val scotiabankBrownsTown = Branch("Brown's Town", "Scotiabank", "B11, Brown's Town","Savanna La Mar", "Westmoreland", "", 0.00, 0.00,"Tamayo Wilson", "Active")
            val scotiabankIronshore = Branch("Ironshore", "Scotiabank", "Shops #2 & 3 Golden Triangle Shopping Centre","Montego Bay", "St James", "", 0.00, 0.00,"Garfield Holness", "Active")
            val scotiabankOchoRios = Branch("Ocho Rios", "Scotiabank", "Main Street P.O. Box 150","Ocho Rios", "St Ann", "", 0.00, 0.00,"Cary Wiggan", "Active")
            val scotiabankFairviewFinancialCentre = Branch("Fairview Financial Centre", "Scotiabank", "1 Port Avenue Fairview","Montego Bay", "Westmoreland", "", 0.00, 0.00,"Paolo Fakhourie", "Active")
            val scotiabankNegril = Branch("Negril", "Scotiabank", "Negril Square","Negril", "Westmoreland", "Savanna La Mar", 0.00, 0.00,"Andrea Rhule-Hudson", "Active")
            val scotiabankChristiana = Branch("Christiana", "Scotiabank", "Main Street","Christiana", "Manchester", "Mandeville", 0.00, 0.00,"Robert Douglas", "Active")
            val scotiabankJunction = Branch("Junction", "Scotiabank", "Shop #1 Tony Rowe Plaza","Junction", "St Elizabeth", "Santa Cruz, Black River, Mandeville", 0.00, 0.00,"Murphy Greg", "Active")
            val scotiabankKingStreet = Branch("King Street", "Scotiabank", "35-45 King Street","Kingston", "Kingston", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Linley Reynolds", "Active")
            val scotiabankMayPen = Branch("May Pen", "Scotiabank", "36 Main Street","May Pen", "Clarendon", "Old Harbour", 0.00, 0.00,"Craig Richards", "Active")
            val scotiabankBlackRiver = Branch("Black River", "Scotiabank", "6 High Street","Black River", "St Elizabeth", "Savanna La Mar, Santa Cruz", 0.00, 0.00,"Pat Thompson", "Active")
            val scotiabankOldHarbour = Branch("Old Harbour", "Scotiabank", "4 South Street","Old Harbour", "St Catherine", "May Pen, Portmore, Portmore, Spanish Town", 0.00, 0.00,"Basil Depass", "Active")
            val scotiabankMandeville = Branch("Mandeville", "Scotiabank", "1A Caledonia Road","Mandeville", "Manchester", "Santa Cruz, Black River, Junction, Christiana", 0.00, 0.00,"Keisha Brown", "Active")
            val scotiabankStAnnsBay = Branch("St Ann's Bay", "Scotiabank", "18 Bravo Street","St Ann's Bay", "St Ann", "Brown's Town, Ocho Rios", 0.00, 0.00,"Denise Hyman", "Active")
            val scotiabankConstantSpring = Branch("Constant Spring", "Scotiabank", "132-132a Constant Spring","Constant Spring", "St Andrew", "New Kingston, Oxford Road, Cross Roads, Hagley Park Road, Half Way Tree", 0.00, 0.00,"Peter Mohan", "Active")

            val scotiabankSantaCruz = Branch("Santa Cruz", "Scotiabank", "77 Main Street","Santa Cruz", "St Elizabeth", "Black River, Junction, Mandeville", 0.00, 0.00,"Kevin Burton", "Active")
            val scotiabankScotiabankCentre = Branch("Scotiabank Centre", "Scotiabank", "Corner Duke & Port Royal","Kingston", "Kingston", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Christopher Samuels", "Active")
            val scotiabankLiguanea = Branch("Liguanea", "Scotiabank", "125-127 Old Hope Road","Liguanea", "St Andrew", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Andrea Douglas", "Active")
            val scotiabankHagleyParkRoad = Branch("Hagley Park Road", "Scotiabank", "128 Hagley Park Road","Hagley Park", "St Andrew", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Stredic Thompson", "Active")
            val scotiabankUWI = Branch("UWI", "Scotiabank", "Corner Ring Road & Shed Lane","Mona", "St Andrew", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Rosemarie Edwards", "Active")
            val scotiabankPortMaria = Branch("Port Maria", "Scotiabank", "57 Warner Street","Port Maria", "St Mary", "Ocho Rios", 0.00, 0.00,"Paul Wallace", "Active")
            val scotiabankNewKingston = Branch("New Kingston", "Scotiabank", "2 Knutsford Boulevard","New Kingston", "St Andrew", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Pamela Douglas", "Active")
            val scotiabankHalfWayTree = Branch("Half Way Tree", "Scotiabank", "82-84 Half Way Tree Road","Half Way Tree", "St Andrew", "Half Way Tree, Cross Roads, Constant Spring", 0.00, 0.00,"Michelle Lee-Gaynor", "Active")
            val scotiabankMorantBay = Branch("Morant Bay", "Scotiabank", "23 Queen Street","Morant Bay", "St Thomas", "", 0.00, 0.00,"Nazeree Baker", "Active")
            val scotiabankLinstead = Branch("Linstead", "Scotiabank", "42 King Street","Linstead", "St Catherine", "", 0.00, 0.00,"Rhoan Bennett", "Active")
            val scotiabankPortAntonio = Branch("Port Antonio", "Scotiabank", "3 Harbour Street","Port Antonio", "Portland", "", 0.00, 0.00,"Mark Thompson", "Active")
            val scotiabankCrossRoads = Branch("Cross Roads", "Scotiabank", "86 Slipe Road","Cross Roads", "St Andrew", "New Kingston, Oxford Road, Half Way Tree", 0.00, 0.00,"Michelle Senior", "Active")
            val scotiabankSpanishTown = Branch("Spanish Town", "Scotiabank", "6 March Pen Road, Shops 25 & 26 Oasis Shopping Plaza","Spanish Town", "St Catherine", "", 0.00, 0.00,"Conrad Wright", "Active")
            val scotiabankOxfordRoad = Branch("Oxford Road", "Scotiabank", "6 Oxford Road","New Kingston", "St Andrew", "New Kingston", 0.00, 0.00,"Hewan Lewis", "Active")
            val scotiabankPortmore = Branch("Portmore", "Scotiabank", "Lot 2 Cookson Pen","Portmore", "St Catherine", "Spanish Town", 0.00, 0.00,"Derrick Palmer", "Active")



            branchDao.addBranch(scotiabankSavannaLaMar)
            branchDao.addBranch(scotiabankFalmouth)
            branchDao.addBranch(scotiabankMontegoBay)
            branchDao.addBranch(scotiabankBrownsTown)
            branchDao.addBranch(scotiabankIronshore)
            branchDao.addBranch(scotiabankOchoRios)
            branchDao.addBranch(scotiabankFairviewFinancialCentre)
            branchDao.addBranch(scotiabankNegril)
            branchDao.addBranch(scotiabankChristiana)
            branchDao.addBranch(scotiabankJunction)
            branchDao.addBranch(scotiabankKingStreet)
            branchDao.addBranch(scotiabankMayPen)
            branchDao.addBranch(scotiabankBlackRiver)
            branchDao.addBranch(scotiabankOldHarbour)
            branchDao.addBranch(scotiabankMandeville)
            branchDao.addBranch(scotiabankStAnnsBay)
            branchDao.addBranch(scotiabankConstantSpring)

            branchDao.addBranch(scotiabankSantaCruz)
            branchDao.addBranch(scotiabankScotiabankCentre)
            branchDao.addBranch(scotiabankLiguanea)
            branchDao.addBranch(scotiabankHagleyParkRoad)
            branchDao.addBranch(scotiabankUWI)
            branchDao.addBranch(scotiabankPortMaria)
            branchDao.addBranch(scotiabankNewKingston)
            branchDao.addBranch(scotiabankHalfWayTree)
            branchDao.addBranch(scotiabankMorantBay)
            branchDao.addBranch(scotiabankLinstead)
            branchDao.addBranch(scotiabankPortAntonio)
            branchDao.addBranch(scotiabankCrossRoads)
            branchDao.addBranch(scotiabankSpanishTown)
            branchDao.addBranch(scotiabankOxfordRoad)
            branchDao.addBranch(scotiabankPortmore)


            /* NCB Branches*/

            val ncbKnutsfordBoulevard= Branch("Knutsford Boulevard", "NCB", "1 Knutsford Boulevard","New Kingston", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbOxfordPlace= Branch("Oxford Place", "NCB", "10 Oxford Road","New Kingston", "St Andrew", "New Kingston", 0.00, 0.00,"Robert Wright", "Active")
            val ncbKHalfWayTree= Branch("Half Way Tree", "NCB", "94 Half Way Tree Road","Half Way Tree", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbCrossRoads= Branch("Cross Roads", "NCB", "90 Cross Roads","Cross Roads", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbSovereignCentre= Branch("Sovereign Centre", "NCB", "Sovereign Centre","Liguanea", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbConstantSpring= Branch("Constant Spring", "NCB", "124 Constant Spring Road","Constant Spring", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbUWI= Branch("University (UWI)", "NCB", "Mona Campus","Mona", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbHagleyPark= Branch("Hagley Park", "NCB", "211 Hagley Park Road","Hagley Park", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbDukeStreet= Branch("Duke Street", "NCB", "37 Duke Street","Kingston", "Kingston", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbWindwardRoad= Branch("Windward Road", "NCB", "89 Windward Road","Kingston", "Kingston", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbPortmore = Branch("Portmore", "NCB", "13 West Trade Way","Portmore", "St Catherine", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbHiloPortmorePines= Branch("Hilo Portmore Pines", "NCB", "Portmore Pines","Portmore", "St Catherine   ", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbStJago = Branch("St. Jago Shopping Centre", "NCB", "Burke Road","St Jago", "St Catherine", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbAnnottoBay = Branch("Annotto Bay", "NCB", "Main Street","Annotto Bay", "St Mary", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbLinstead = Branch("Linstead", "NCB", "29 King Street","Linstead", "St Catherine", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbPortAntonio= Branch("Port Antonio", "NCB", "Gideon Avenue","Port Antonio", "Portland ", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbPortMaria= Branch("Port Maria", "NCB", "8 Main Street","Port Maria", "Mary Mary", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbMorantBay= Branch("Morant Bay", "NCB", "36 Queen Street","Morant Bay", "St Thomas", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbMayPen= Branch("May Pen", "NCB", "41 Main Street","May Pen", "Clarendon", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbChapelton= Branch("Chapelton", "NCB", "Main Street","Chapelton", "Clarendon", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbOchoRios= Branch("Ocho Rios", "NCB", "40 Main Street","Ocho Rios", "St Ann", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbSpalding= Branch("Spalding", "NCB", "Spalding","Spalding", "Clarendon", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbBrownsTown= Branch("Brown's Town", "NCB", "17 Main Street","Brown's Townn", "St Ann", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbChristiana= Branch("Christiana", "NCB", "Main Street","Christiana", "Manchester", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbMandeville= Branch("Mandeville", "NCB", "6 Perth Road","Mandeville", "Manchester", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbJunction= Branch("Junction", "NCB", "Junction","Junction", "St Elizabeth", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbGayleSupermarket = Branch("Gayle Supermarket", "NCB", "Southfield","Southfield", "St Elizabeth", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbSantaCruz= Branch("Santa Cruz", "NCB", "7 Coke Drive","Santa Cruz", "St Elizabeth", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbFalmouth = Branch("Falmouth", "NCB", "Water Square","Falmouth", "Trelawny", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbKnutsfordBoulevard= Branch("KnutsfordBoulevard", "NCB", "1 Knutsford Boulevard","New Kingston", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbKnutsfordBoulevard= Branch("KnutsfordBoulevard", "NCB", "1 Knutsford Boulevard","New Kingston", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbKnutsfordBoulevard= Branch("KnutsfordBoulevard", "NCB", "1 Knutsford Boulevard","New Kingston", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbKnutsfordBoulevard= Branch("KnutsfordBoulevard", "NCB", "1 Knutsford Boulevard","New Kingston", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")
            val ncbKnutsfordBoulevard= Branch("KnutsfordBoulevard", "NCB", "1 Knutsford Boulevard","New Kingston", "St Andrew", "", 0.00, 0.00,"Robert Wright", "Active")


        }
    }


}




