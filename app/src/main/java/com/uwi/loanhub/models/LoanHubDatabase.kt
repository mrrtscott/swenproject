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


@Database(entities = arrayOf(User::class, Loan::class, Institution::class, LoanRequirement::class, LoanLikes::class, LoanRating::class, InstitutionAssets::class, Branch::class, Glossary::class, Tips::class), version = 22, exportSchema = false)
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
    abstract fun GlossaryDao():GlossaryDao
    abstract fun TipsDao():TipsDao


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
                    populateScotiaBranch(database.BranchDao())
                    populateNCBBranch(database.BranchDao())
                    populateFGBBranch(database.BranchDao())
                    populateSagicorBranch(database.BranchDao())
                    populateJMMBBranch(database.BranchDao())
                    populateCIBC(database.BranchDao())
                    populateVMBranch(database.BranchDao())
                    populateJN(database.BranchDao())
                    populateGlossary(database.GlossaryDao())
                    populateTips(database.TipsDao())










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
                6000000,
                4.5,
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
                "Sagicor",
                "Loanara",
                9050000,
                11.5,
                "120 months",
                80.0,
                50,
                "Get 100% financing on new and used vehicles 2013 and newer. Sign Up For Newsletter. Apply Online.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_1)

            val loan_2 = Loan(
                0,
                "JMMB",
                "Zoom Loan",
                7500000,
                10.5,
                "110 months",
                80.0,
                90,
                "Get a personal loan with no hidden fees, no prepayment penalties, and no origination fees from one of the most trusted loan companies in the market.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_2)

            val loan_3 = Loan(
                0,
                "Scotiabank",
                "Scoot Loan",
                80000,
                10.5,
                "80 months",
                70.0,
                90,
                "Fast Application, Competitive Rates And Quick Decisions. Apply for a new or used car loan or refinance your existing auto loan your bank.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_3)


            val loan_4 = Loan(
                0,
                "Sagicor",
                "Turbo Loan",
                2000000,
                10.5,
                "80 months",
                85.0,
                70,
                "Get 100% financing on new and used vehicles 2013 and newer.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_4)



            val loan_5 = Loan(
                0,
                "JMMB",
                "Smart Loan",
                25000,
                10.5,
                "80 months",
                80.0,
                90,
                "Apply online for a new or used car loan from this bank. Get approved for a financing based on your needs and within your budget.",
                "Female",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_5)


            val loan_6 = Loan(
                0,
            "JN Bank",
            "Drive Loan",
            28000,
            10.5,
            "80 months",
            85.0,
            90,
            "Car Loans. Get your rates quickly on new, used and refinanced loans.",
            "Male",
            "Active",
            "2020-09-01"
            )
            loansDao.addNewLoan(loan_6)



            val loan_7 = Loan(
                0,
                "First Global",
                "Quick Loan",
                190000,
                10.5,
                "Get terms up to 84 months and no payments for 60 days.",
                98.0,
                90,
                "This is a CIBC loan description",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_7)

            val loan_8 = Loan(
                0,
                "CIBC",
                "Infinite Loan",
                380000,
                10.5,
                "80 months",
                80.0,
                95,
                "We don't hide any fees, so you know exactly how much you'll pay.",
                "Female",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_8)

            val loan_9 = Loan(
                0,
                "Sagicor",
                "Swift Loan",
                980000,
                10.5,
                "80 months",
                65.0,
                90,
                "We never penalize you for paying off your loan early.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_9)

            val loan_10 = Loan(
                0,
                "CIBC",
                "Anchor Loan",
                790000,
                7.5,
                "80 months",
                80.0,
                60,
                "Our simple application only takes a few minutes.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_10)

            val loan_11 = Loan(
                0,
                "Scotiabank",
                "Ace Loan",
                850000,
                11.5,
                "80 months",
                50.0,
                70,
                "Refinance your car loan with this bank and lower monthly payments.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_11)

            val loan_12 = Loan(
                0,
                "Sagicor",
                "King Loan",
                19000000,
                10.7,
                "120 months",
                100.0,
                45,
                "Our low rates on auto loans make us one of the top auto lenders in Jamaica for new and used vehicles, auto refinance loans, lease buyouts, RV loans.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_12)

            val loan_13 = Loan(
                0,
                "Sagicor",
                "Dash Loan",
                90000,
                10.5,
                "80 months",
                8.0,
                90,
                "Whether you're buying, refinancing or looking to turn your lease into a loan, this bank offers a solution for you. Buy the car you want with confidence.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_13)

            val loan_14 = Loan(
                0,
                "Scotiabank",
                "Easy Loan",
                90000,
                10.5,
                "80 months",
                100.0,
                90,
                "Be more confident when buying your next car or vehicle. Find an auto loan with our bank that fits your budget. See all financing options & apply now.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_14)

            val loan_15= Loan(
                0,
                "JN Bank",
                "Hustle Loan",
                90000,
                10.5,
                "80 months",
                100.0,
                90,
                "Fast Approval. Contact Us Today For A Quote!",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_15)

            val loan_16 = Loan(
                0,
                "JN Bank",
                "Atlas Loan",
                2900000,
                10.5,
                "72 months",
                100.0,
                50,
                "We give you the opportunity to purchase a new or used motor vehicle with a secured loan so that you can drive away in style. ",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_16)

            val loan_17 = Loan(
                0,
                "Scotiabank",
                "Cornerstone Loan",
                6900000,
                12.5,
                "60 months",
                85.0,
                60,
                "With low auto loan rates for new and used vehicles, we make it easy to get the ride you want.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_17)

            val loan_18 = Loan(
                0,
                "NCB",
                "Top Loan",
                4790000,
                10.5,
                "80 months",
                50.0,
                90,
                "Be more confident when buying your next car or vehicle.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_18)

            val loan_19 = Loan(
                0,
                "Victoria Mutual",
                "Brisk Loan",
                8000000,
                10.5,
                "120 months",
                70.0,
                80,
                "Accelerate Your Vehicle Purchase. Whether you're purchasing new or used or looking to refinance a vehicle.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_19)

            val loan_20 = Loan(
                0,
                "JN Bank",
                "Pro Loan",
                90000,
                10.5,
                "80 months",
                65.0,
                90,
                "We offer flexible auto loan and motorcycle loan options. Get the car you want at a rate you'll love! Dreaming of a new car?",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_20)

            val loan_21 = Loan(
                0,
                "CIBC",
                "Express Loan",
                9000000,
                10.5,
                "80 months",
                90.0,
                90,
                "Let's steer you in the right direction. Take control of your payments. Explore our great car loan rates and apply for a loan online today.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_21)

            val loan_22 = Loan(
                0,
                "NCB",
                "Optimum Loan",
                3000000,
                10.25,
                "80 months",
                80.0,
                90,
                "We make it simple for you to choose your auto loan options and payments ahead of time. ",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_22)

            val loan_23 = Loan(
                0,
                "CIBC",
                "Elite Loan",
                4000000,
                10.5,
                "72 months",
                90.0,
                90,
                "A low-rate car loan will put you behind the wheel and help you keep a little extra money in your pocket at the same time.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_23)

            val loan_24 = Loan(
                0,
                "Victoria Mutual",
                "Dart Loan",
                1000000,
                15.5,
                "24 months",
                100.0,
                60,
                "Relax ... just drive. Whether you're after that new-car smell or just want to save money by refinancing your loan from another financial institution",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_24)

            val loan_25 = Loan(
                0,
                "Scotiabank",
                "Path Loan",
                9000000,
                10.5,
                "80 months",
                85.0,
                70,
                "Buy a new or used vehicle (car, truck, RV, or motorcycle). Benefit from competitive rates and a quick approval process.",
                "Female",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_25)

            val loan_26 = Loan(
                0,
                "JMMB",
                "Sturdy Loan",
                4000000,
                10.5,
                "52 months",
                85.0,
                90,
                "Take the stress out of shopping for a vehicle.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_26)

            val loan_27 = Loan(
                0,
                "First Global",
                "Bound Loan",
                90000,
                10.5,
                "80 months",
                100.0,
                90,
                "A car loan is secured against the vehicle you intend to purchase, which means the vehicle serves as collateral for the loan.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_27)

            val loan_28 = Loan(
                0,
                "First Global",
                "Crypto Loan",
                6000000,
                11.75,
                "80 months",
                100.0,
                70,
                "No matter how you buy your next car, our auto loans offer: Instant loan decisions; Discounted interest rate available when you set up automatic payments.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_28)

            val loan_29 = Loan(
                0,
                "JMMB",
                "GoLoan",
                5000000,
                6.5,
                "80 months",
                85.0,
                90,
                "The interest rate on car loans is calculated on the daily reducing balance and is based on the credit score.",
                "Female",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_29)

            val loan_30 = Loan(
                0,
                "CIBC",
                "Post Loan",
                90000,
                6.75,
                "80 months",
                100.0,
                90,
                "Get pre-approved before you buy a new or used vehicle or refinance your existing auto loan. ",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_30)

            val loan_31 = Loan(
                0,
                "JN Bank",
                "Loanica",
                20000000,
                10.5,
                "70 months",
                100.0,
                90,
                "Car Buying Made Easy. We have auto loans for your budget.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_31)

            val loan_32 = Loan(
                0,
                "CIBC",
                "Loanus",
                9000000,
                10.5,
                "70 months",
                80.0,
                90,
                "Auto loan rates and terms are good for new and used cars and trucks.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_32)

            val loan_33 = Loan(
                0,
                "CIBC",
                "Real Loan",
                8000000,
                10.5,
                "80 months",
                100.0,
                60,
                "We can help you save money when purchasing a new or used auto or by refinancing a higher interest rate loan.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_33)

            val loan_34 = Loan(
                0,
                "JN Bank",
                "Loanzio",
                7000000,
                15.5,
                "80 months",
                80.0,
                60,
                "Whether you're shopping for a hot car or a minivan, we'll help you choose from a wide variety of financing options made to accommodate every vehicle.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_34)

            val loan_35 = Loan(
                0,
                "NCB",
                "mloan",
                90000,
                10.5,
                "80 months",
                100.0,
                70,
                "Looking for an easy, hassle-free way to finance your next vehicle? Whether you' re buying new or used, you'll get a great rate on an auto loan.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_35)

            val loan_36 = Loan(
                0,
                "Victoria Mutual",
                "Loanvoyage",
                12000000,
                6.5,
                "80 months",
                85.0,
                90,
                "Not only do we offer great rates and personal service, but we don't add costly fees and penalties. Hit the road with a Vehicle Loan that saves you money!",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_36)

            val loan_37 = Loan(
                0,
                "NCB",
                "Bux Loan",
                7000000,
                11.5,
                "80 months",
                90.0,
                80,
                "We believe getting a car loan should be convenient and come with personal service.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_37)

            val loan_38 = Loan(
                0,
                "NCB",
                "Lotus Loan",
                90000,
                10.5,
                "80 months",
                90.0,
                90,
                "Looking for an Auto Loan in Florida? We've Got You Covered.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_38)

            val loan_39 = Loan(
                0,
                "Victoria Mutual",
                "Angel Loan",
                90000,
                10.5,
                "80 months",
                100.0,
                90,
                "Grab the keys and go. When it's time for a new or used vehicle – don't let financing hassles get in the way.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_39)

            val loan_40 = Loan(
                0,
                "Scotiabank",
                "Stack Loan",
                90000,
                10.5,
                "80 months",
                100.0,
                90,
                "Whether you're buying a new or used car, we can help make your purchase a little easier.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_40)

            val loan_41 = Loan(
                0,
                "JMMB",
                "Covered Loan",
                8000000,
                8.5,
                "80 months",
                100.0,
                70,
                "We believe getting a car loan should be convenient and come with personal service. When you finance your new car, truck, or SUV.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_41)

            val loan_42 = Loan(
                0,
                "CIBC",
                "Bite Loan",
                3000000,
                10.5,
                "72 months",
                90.0,
                60,
                " Whether you're buying a new car, a used car, or just refinancing your existing auto loan.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_42)

            val loan_43 = Loan(
                0,
                "Sagicor",
                "Sact Loan",
                8000000,
                12.5,
                "80 months",
                90.0,
                90,
                "Get Rates Fast, Call Now. Auto Loans & Financial Service. Select Your Car, We'll Fiance It & Get You Going. Apply Today. Low Monthly Payments. Low Processing Fees",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_43)

            val loan_44 = Loan(
                0,
                "JN Bank",
                "Butt Loan",
                40000000,
                12.5,
                "80 months",
                100.0,
                90,
                "Affordable rates and flexible terms for new and used car, rv and boat loans. Already have a car loan? Refinance and get cash back!",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_44)

            val loan_45 = Loan(
                0,
                "NCB",
                "Helix Loan",
                20000000,
                10.5,
                "84 months",
                70.0,
                85,
                "Car Buying Made Easy. Helping you get the car you want, fast. Whether you're purchasing new or used, we're here to help you finance your new vehicle.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_45)

            val loan_46 = Loan(
                0,
                "First Global",
                "Plow Loan",
                4000000,
                11.25,
                "80 months",
                100.0,
                90,
                "We make the car buying process easy with competitive rates, flexible terms.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_46)

            val loan_47 = Loan(
                0,
                "NCB",
                "Thirs Loan",
                6000000,
                9.5,
                "80 months",
                100.0,
                90,
                "We have the BEST loan offerings that will have you driving your dream car in no time.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_47)

            val loan_48 = Loan(
                0,
                "First Global",
                "Billzo Loan",
                3000000,
                9.5,
                "60 months",
                90.0,
                90,
                "Drive away happy with a low rate auto loan.",
                "Male",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_48)

            val loan_49 = Loan(
                0,
                "Victoria Mutual",
                "Pier Loan",
                9000000,
                11.25,
                "72 months",
                80.0,
                60,
                "Getting a car loan doesn't have to be a major journey.",
                "Everybody",
                "Active",
                "2020-09-01"
            )
            loansDao.addNewLoan(loan_49)

            val loan_50 = Loan(
                0,
                "Victoria Mutual",
                "Wallet Loan",
                10000000,
                10.5,
                "72 months",
                90.0,
                85,
                "Finance your dream car with Victoria Mutual. Get the money you need to claim the wheels you want.",
                "Male",
                "Everybody",
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


        suspend fun populateScotiaBranch(branchDao: BranchDao) {

            val scotiabankSavannaLaMar = Branch(
                "Savanna La Mar",
                "Scotiabank",
                "19 Great George's Street",
                "Savanna La Mar",
                "Westmoreland",
                "Negril",
                0.00,
                0.00,
                "Caswell Dawes",
                "Active"
            )

            val scotiabankFalmouth = Branch(
                "Falmouth",
                "Scotiabank",
                "Trewlany Wharf",
                "Falmouth",
                "Trelawny",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val scotiabankMontegoBay = Branch(
                "Montego Bay",
                "Scotiabank",
                "6-7 Sam Sharpe Square",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "Rayon Clarke",
                "Active"
            )
            val scotiabankBrownsTown = Branch(
                "Brown's Town",
                "Scotiabank",
                "B11, Brown's Town",
                "Savanna La Mar",
                "Westmoreland",
                "",
                0.00,
                0.00,
                "Tamayo Wilson",
                "Active"
            )
            val scotiabankIronshore = Branch(
                "Ironshore",
                "Scotiabank",
                "Shops #2 & 3 Golden Triangle Shopping Centre",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "Garfield Holness",
                "Active"
            )
            val scotiabankOchoRios = Branch(
                "Ocho Rios",
                "Scotiabank",
                "Main Street P.O. Box 150",
                "Ocho Rios",
                "St Ann",
                "",
                0.00,
                0.00,
                "Cary Wiggan",
                "Active"
            )
            val scotiabankFairviewFinancialCentre = Branch(
                "Fairview Financial Centre",
                "Scotiabank",
                "1 Port Avenue Fairview",
                "Montego Bay",
                "Westmoreland",
                "",
                0.00,
                0.00,
                "Paolo Fakhourie",
                "Active"
            )
            val scotiabankNegril = Branch(
                "Negril",
                "Scotiabank",
                "Negril Square",
                "Negril",
                "Westmoreland",
                "Savanna La Mar",
                0.00,
                0.00,
                "Andrea Rhule-Hudson",
                "Active"
            )
            val scotiabankChristiana = Branch(
                "Christiana",
                "Scotiabank",
                "Main Street",
                "Christiana",
                "Manchester",
                "Mandeville",
                0.00,
                0.00,
                "Robert Douglas",
                "Active"
            )
            val scotiabankJunction = Branch(
                "Junction",
                "Scotiabank",
                "Shop #1 Tony Rowe Plaza",
                "Junction",
                "St Elizabeth",
                "Santa Cruz, Black River, Mandeville",
                0.00,
                0.00,
                "Murphy Greg",
                "Active"
            )
            val scotiabankKingStreet = Branch(
                "King Street",
                "Scotiabank",
                "35-45 King Street",
                "Kingston",
                "Kingston",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Linley Reynolds",
                "Active"
            )
            val scotiabankMayPen = Branch(
                "May Pen",
                "Scotiabank",
                "36 Main Street",
                "May Pen",
                "Clarendon",
                "Old Harbour",
                0.00,
                0.00,
                "Craig Richards",
                "Active"
            )
            val scotiabankBlackRiver = Branch(
                "Black River",
                "Scotiabank",
                "6 High Street",
                "Black River",
                "St Elizabeth",
                "Savanna La Mar, Santa Cruz",
                0.00,
                0.00,
                "Pat Thompson",
                "Active"
            )
            val scotiabankOldHarbour = Branch(
                "Old Harbour",
                "Scotiabank",
                "4 South Street",
                "Old Harbour",
                "St Catherine",
                "May Pen, Portmore, Portmore, Spanish Town",
                0.00,
                0.00,
                "Basil Depass",
                "Active"
            )
            val scotiabankMandeville = Branch(
                "Mandeville",
                "Scotiabank",
                "1A Caledonia Road",
                "Mandeville",
                "Manchester",
                "Santa Cruz, Black River, Junction, Christiana",
                0.00,
                0.00,
                "Keisha Brown",
                "Active"
            )
            val scotiabankStAnnsBay = Branch(
                "St Ann's Bay",
                "Scotiabank",
                "18 Bravo Street",
                "St Ann's Bay",
                "St Ann",
                "Brown's Town, Ocho Rios",
                0.00,
                0.00,
                "Denise Hyman",
                "Active"
            )
            val scotiabankConstantSpring = Branch(
                "Constant Spring",
                "Scotiabank",
                "132-132a Constant Spring",
                "Constant Spring",
                "St Andrew",
                "New Kingston, Oxford Road, Cross Roads, Hagley Park Road, Half Way Tree",
                0.00,
                0.00,
                "Peter Mohan",
                "Active"
            )

            val scotiabankSantaCruz = Branch(
                "Santa Cruz",
                "Scotiabank",
                "77 Main Street",
                "Santa Cruz",
                "St Elizabeth",
                "Black River, Junction, Mandeville",
                0.00,
                0.00,
                "Kevin Burton",
                "Active"
            )
            val scotiabankScotiabankCentre = Branch(
                "Scotiabank Centre",
                "Scotiabank",
                "Corner Duke & Port Royal",
                "Kingston",
                "Kingston",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Christopher Samuels",
                "Active"
            )
            val scotiabankLiguanea = Branch(
                "Liguanea",
                "Scotiabank",
                "125-127 Old Hope Road",
                "Liguanea",
                "St Andrew",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Andrea Douglas",
                "Active"
            )
            val scotiabankHagleyParkRoad = Branch(
                "Hagley Park Road",
                "Scotiabank",
                "128 Hagley Park Road",
                "Hagley Park",
                "St Andrew",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Stredic Thompson",
                "Active"
            )
            val scotiabankUWI = Branch(
                "UWI",
                "Scotiabank",
                "Corner Ring Road & Shed Lane",
                "Mona",
                "St Andrew",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Rosemarie Edwards",
                "Active"
            )
            val scotiabankPortMaria = Branch(
                "Port Maria",
                "Scotiabank",
                "57 Warner Street",
                "Port Maria",
                "St Mary",
                "Ocho Rios",
                0.00,
                0.00,
                "Paul Wallace",
                "Active"
            )
            val scotiabankNewKingston = Branch(
                "New Kingston",
                "Scotiabank",
                "2 Knutsford Boulevard",
                "New Kingston",
                "St Andrew",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Pamela Douglas",
                "Active"
            )
            val scotiabankHalfWayTree = Branch(
                "Half Way Tree",
                "Scotiabank",
                "82-84 Half Way Tree Road",
                "Half Way Tree",
                "St Andrew",
                "Half Way Tree, Cross Roads, Constant Spring",
                0.00,
                0.00,
                "Michelle Lee-Gaynor",
                "Active"
            )
            val scotiabankMorantBay = Branch(
                "Morant Bay",
                "Scotiabank",
                "23 Queen Street",
                "Morant Bay",
                "St Thomas",
                "",
                0.00,
                0.00,
                "Nazeree Baker",
                "Active"
            )
            val scotiabankLinstead = Branch(
                "Linstead",
                "Scotiabank",
                "42 King Street",
                "Linstead",
                "St Catherine",
                "",
                0.00,
                0.00,
                "Rhoan Bennett",
                "Active"
            )
            val scotiabankPortAntonio = Branch(
                "Port Antonio",
                "Scotiabank",
                "3 Harbour Street",
                "Port Antonio",
                "Portland",
                "",
                0.00,
                0.00,
                "Mark Thompson",
                "Active"
            )
            val scotiabankCrossRoads = Branch(
                "Cross Roads",
                "Scotiabank",
                "86 Slipe Road",
                "Cross Roads",
                "St Andrew",
                "New Kingston, Oxford Road, Half Way Tree",
                0.00,
                0.00,
                "Michelle Senior",
                "Active"
            )
            val scotiabankSpanishTown = Branch(
                "Spanish Town",
                "Scotiabank",
                "6 March Pen Road, Shops 25 & 26 Oasis Shopping Plaza",
                "Spanish Town",
                "St Catherine",
                "",
                0.00,
                0.00,
                "Conrad Wright",
                "Active"
            )
            val scotiabankOxfordRoad = Branch(
                "Oxford Road",
                "Scotiabank",
                "6 Oxford Road",
                "New Kingston",
                "St Andrew",
                "New Kingston",
                0.00,
                0.00,
                "Hewan Lewis",
                "Active"
            )
            val scotiabankPortmore = Branch(
                "Portmore",
                "Scotiabank",
                "Lot 2 Cookson Pen",
                "Portmore",
                "St Catherine",
                "Spanish Town",
                0.00,
                0.00,
                "Derrick Palmer",
                "Active"
            )



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
        }


        suspend fun populateNCBBranch(branchDao: BranchDao) {

            /* NCB Branches*/

            val ncbKnutsfordBoulevard = Branch(
                "Knutsford Boulevard",
                "NCB",
                "1 Knutsford Boulevard",
                "New Kingston",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbOxfordPlace = Branch(
                "Oxford Place",
                "NCB",
                "10 Oxford Road",
                "New Kingston",
                "St Andrew",
                "New Kingston",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbKHalfWayTree = Branch(
                "Half Way Tree",
                "NCB",
                "94 Half Way Tree Road",
                "Half Way Tree",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbCrossRoads = Branch(
                "Cross Roads",
                "NCB",
                "90 Cross Roads",
                "Cross Roads",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbSovereignCentre = Branch(
                "Sovereign Centre",
                "NCB",
                "Sovereign Centre",
                "Liguanea",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbConstantSpring = Branch(
                "Constant Spring",
                "NCB",
                "124 Constant Spring Road",
                "Constant Spring",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbUWI = Branch(
                "University (UWI)",
                "NCB",
                "Mona Campus",
                "Mona",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbHagleyPark = Branch(
                "Hagley Park",
                "NCB",
                "211 Hagley Park Road",
                "Hagley Park",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbDukeStreet = Branch(
                "Duke Street",
                "NCB",
                "37 Duke Street",
                "Kingston",
                "Kingston",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbWindwardRoad = Branch(
                "Windward Road",
                "NCB",
                "89 Windward Road",
                "Kingston",
                "Kingston",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbPortmore = Branch(
                "Portmore",
                "NCB",
                "13 West Trade Way",
                "Portmore",
                "St Catherine",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbHiloPortmorePines = Branch(
                "Hilo Portmore Pines",
                "NCB",
                "Portmore Pines",
                "Portmore",
                "St Catherine   ",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbStJago = Branch(
                "St. Jago Shopping Centre",
                "NCB",
                "Burke Road",
                "St Jago",
                "St Catherine",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbAnnottoBay = Branch(
                "Annotto Bay",
                "NCB",
                "Main Street",
                "Annotto Bay",
                "St Mary",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbLinstead = Branch(
                "Linstead",
                "NCB",
                "29 King Street",
                "Linstead",
                "St Catherine",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbPortAntonio = Branch(
                "Port Antonio",
                "NCB",
                "Gideon Avenue",
                "Port Antonio",
                "Portland ",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbPortMaria = Branch(
                "Port Maria",
                "NCB",
                "8 Main Street",
                "Port Maria",
                "Mary Mary",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbMorantBay = Branch(
                "Morant Bay",
                "NCB",
                "36 Queen Street",
                "Morant Bay",
                "St Thomas",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbMayPen = Branch(
                "May Pen",
                "NCB",
                "41 Main Street",
                "May Pen",
                "Clarendon",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbChapelton = Branch(
                "Chapelton",
                "NCB",
                "Main Street",
                "Chapelton",
                "Clarendon",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbOchoRios = Branch(
                "Ocho Rios",
                "NCB",
                "40 Main Street",
                "Ocho Rios",
                "St Ann",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbSpalding = Branch(
                "Spalding",
                "NCB",
                "Spalding",
                "Spalding",
                "Clarendon",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbBrownsTown = Branch(
                "Brown's Town",
                "NCB",
                "17 Main Street",
                "Brown's Townn",
                "St Ann",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbChristiana = Branch(
                "Christiana",
                "NCB",
                "Main Street",
                "Christiana",
                "Manchester",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbMandeville = Branch(
                "Mandeville",
                "NCB",
                "6 Perth Road",
                "Mandeville",
                "Manchester",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbJunction = Branch(
                "Junction",
                "NCB",
                "Junction",
                "Junction",
                "St Elizabeth",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbGayleSupermarket = Branch(
                "Gayle Supermarket",
                "NCB",
                "Southfield",
                "Southfield",
                "St Elizabeth",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbSantaCruz = Branch(
                "Santa Cruz",
                "NCB",
                "7 Coke Drive",
                "Santa Cruz",
                "St Elizabeth",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbFalmouth = Branch(
                "Falmouth",
                "NCB",
                "Water Square",
                "Falmouth",
                "Trelawny",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbBlackRiver = Branch(
                "Black River",
                "NCB",
                "1 Brigade Street",
                "Black River",
                "St Elizabeth",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbStJamesStreet = Branch(
                "St James Street",
                "NCB",
                "41 St. James Street",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbBaywest = Branch(
                "Baywest",
                "NCB",
                "Harbour Street",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbSavannaLaMar = Branch(
                "Savanna La Mar",
                "NCB",
                "68 Great George Street",
                "Savanna La Mar",
                "Westmoreland",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )
            val ncbLucea = Branch(
                "Lucea",
                "NCB",
                "Lucea",
                "Lucea",
                "Hanover",
                "",
                0.00,
                0.00,
                "Robert Wright",
                "Active"
            )



            branchDao.addBranch(ncbKnutsfordBoulevard)
            branchDao.addBranch(ncbOxfordPlace)
            branchDao.addBranch(ncbKHalfWayTree)
            branchDao.addBranch(ncbCrossRoads)
            branchDao.addBranch(ncbSovereignCentre)
            branchDao.addBranch(ncbConstantSpring)
            branchDao.addBranch(ncbUWI)
            branchDao.addBranch(ncbHagleyPark)
            branchDao.addBranch(ncbDukeStreet)
            branchDao.addBranch(ncbWindwardRoad)
            branchDao.addBranch(ncbPortmore)
            branchDao.addBranch(ncbHiloPortmorePines)
            branchDao.addBranch(ncbStJago)
            branchDao.addBranch(ncbAnnottoBay)
            branchDao.addBranch(ncbLinstead)
            branchDao.addBranch(ncbPortAntonio)
            branchDao.addBranch(ncbPortMaria)
            branchDao.addBranch(ncbMorantBay)
            branchDao.addBranch(ncbMayPen)
            branchDao.addBranch(ncbChapelton)
            branchDao.addBranch(ncbOchoRios)
            branchDao.addBranch(ncbSpalding)
            branchDao.addBranch(ncbBrownsTown)
            branchDao.addBranch(ncbChristiana)
            branchDao.addBranch(ncbMandeville)
            branchDao.addBranch(ncbJunction)
            branchDao.addBranch(ncbGayleSupermarket)
            branchDao.addBranch(ncbSantaCruz)
            branchDao.addBranch(ncbFalmouth)
            branchDao.addBranch(ncbBlackRiver)
            branchDao.addBranch(ncbStJamesStreet)
            branchDao.addBranch(ncbBaywest)
            branchDao.addBranch(ncbSavannaLaMar)
            branchDao.addBranch(ncbLucea)


        }


        suspend fun populateFGBBranch(branchDao: BranchDao) {

            val firstGlobalDowntown = Branch(
                "Downtown",
                "First Global",
                "2 Duke Street",
                "Kingston",
                "Kingston",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalLiguanea = Branch(
                "Liguanea",
                "First Global",
                " Shop 27, Lane Plaza, 121 Old Hope Road",
                "Liguanea",
                "St Andrew",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalMandeville = Branch(
                "Mandeville",
                "First Global",
                "2-4 Ward Avenue",
                "Mandeville",
                "Manchester",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalOchoRios = Branch(
                "Ocho Rios",
                "First Global",
                "Shop 25, Soni's Plaza, 50 Main Street",
                "ocho Rios",
                "St Ann",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalNewKingston = Branch(
                "New Kingston",
                "First Global",
                "28-48 Barbados Avenue",
                "New Kingston",
                "St Andrew",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalManorPark = Branch(
                "Manor Park",
                "First Global",
                "",
                "184 Constant Spring Road",
                "St Andrew",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalMontegoBay = Branch(
                "Montego Bay",
                "First Global",
                "Fairview Shopping Centre",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val firstGlobalPortmore = Branch(
                "Portmore",
                "First Global",
                "Portmore Pines Plaza",
                "Portmore",
                "St Catherine",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )


            branchDao.addBranch(firstGlobalDowntown)
            branchDao.addBranch(firstGlobalLiguanea)
            branchDao.addBranch(firstGlobalMandeville)
            branchDao.addBranch(firstGlobalOchoRios)
            branchDao.addBranch(firstGlobalNewKingston)
            branchDao.addBranch(firstGlobalManorPark)
            branchDao.addBranch(firstGlobalMontegoBay)
            branchDao.addBranch(firstGlobalPortmore)
        }




        suspend fun populateSagicorBranch(branchDao: BranchDao) {


            val sagicorHO = Branch(
                "Head Office",
                "Sagicor",
                "28-48 Barbados Avenue",
                "New Kingston",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorUpParkCamp = Branch(
                "UpPark Camp",
                "Sagicor",
                "",
                "Kingston",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorDominicaDrive = Branch(
                "Dominica Drive",
                "Sagicor",
                "17 Dominica Drive",
                "New Kingston",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorTropicalPlaza = Branch(
                "Tropical Plaza",
                "Sagicor",
                "12 1/2 & 14 Constant Spring Rd",
                "Half Way Tree",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorBlackRiver = Branch(
                "Black River",
                "Sagicor",
                "Corner of High and School Streets",
                "Black River",
                "St Elizabeth",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicoOchoRios = Branch(
                "Ocho Rios",
                "Sagicor",
                "Eight Rivers Towne Centre, Buckfield",
                "Ocho Rios",
                "St Ann",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorMandeville = Branch(
                "Mandeville",
                "Sagicor",
                "5-7 Ward Ave",
                "Mandeville",
                "Manchester",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorBankDukeTowerStreet = Branch(
                "Bank Duke and Tower Street",
                "Sagicor",
                "17a Duke Street",
                "Kingston",
                "Kingston",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorHalfWayTree = Branch(
                "Half Way Tree",
                "Sagicor",
                "6C Constant Spring Rd",
                "Half Way Tree",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorHopeRoad = Branch(
                "Hope Road",
                "Sagicor",
                "85 Hope Road",
                "Hope Road",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorFairview = Branch(
                "Fairview",
                "Sagicor",
                "21B Fairview Shopping Centre",
                "Montego Bay",
                "St James",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorManorPark = Branch(
                "Manor Park",
                "Sagicor",
                "184 Constant Spring Road",
                "Manor Park",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorSavannaLaMar = Branch(
                "Savanna La Mar",
                "Sagicor",
                "Great George St",
                "Savanna La Mar",
                "Westmoreland",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorMayPen = Branch(
                "May Pen",
                "Sagicor",
                "6B Manchester Ave",
                "May Pen",
                "Clarendon",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorPortmore = Branch(
                "Portmore",
                "Sagicor",
                "Portmore Pines Plaza",
                "Portmore",
                "St Catherine",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorLiguanea = Branch(
                "Liguanea",
                "Sagicor",
                "106 Hope Road",
                "Liguanea",
                "St Andrew",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )
            val sagicorMontegoBay = Branch(
                "Montego Bay",
                "Sagicor",
                "Commercial Shopping Centre, Howard Cooke Blvd",
                "Montego Bay",
                "St James",
                "",
                0.0,
                0.0,
                "",
                "Active"
            )


            branchDao.addBranch(sagicorHO)
            branchDao.addBranch(sagicorUpParkCamp)
            branchDao.addBranch(sagicorDominicaDrive)
            branchDao.addBranch(sagicorTropicalPlaza)
            branchDao.addBranch(sagicorBlackRiver)
            branchDao.addBranch(sagicoOchoRios)
            branchDao.addBranch(sagicorMandeville)
            branchDao.addBranch(sagicorBankDukeTowerStreet)
            branchDao.addBranch(sagicorHalfWayTree)
            branchDao.addBranch(sagicorHopeRoad)
            branchDao.addBranch(sagicorFairview)
            branchDao.addBranch(sagicorManorPark)
            branchDao.addBranch(sagicorSavannaLaMar)
            branchDao.addBranch(sagicorMayPen)
            branchDao.addBranch(sagicorPortmore)
            branchDao.addBranch(sagicorLiguanea)
            branchDao.addBranch(sagicorMontegoBay)

        }


        suspend fun populateJMMBBranch(branchDao: BranchDao) {


            val jmmbHO = Branch(
                "Head Office",
                "JMMB",
                "6 Haughton Terrace",
                "New Kingston",
                "St Andrew",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbKnutsfordBoulevard = Branch(
                "Knutsford Boulevard",
                "JMMB",
                "11 Knutsford Boulevard",
                "New Kingston",
                "St Andrew",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbOchoRios = Branch(
                "Ocho Rios",
                "JMMB",
                "2 Graham Street",
                "Ocho Rios",
                "St Ann",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbPortmore = Branch(
                "Portmore",
                "JMMB",
                "47-48 West Trade Way",
                "Portmore",
                "St Catherine",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbJunction = Branch(
                "Junction",
                "JMMB",
                "",
                "Junction",
                "St Elizabeth",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbChurchStreet = Branch(
                "Montego Bay Church Street",
                "JMMB",
                "25 Church Street",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbHaughtonAvenue = Branch(
                "Haughton Avenue",
                "JMMB",
                "5 Haughton Avenue",
                "New Kingston",
                "St Andrew",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbMandeville = Branch(
                "Mandeville",
                "JMMB",
                "23 Ward Avenue",
                "Mandeville",
                "Manchester",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbMayPen = Branch(
                "May Pen",
                "JMMB",
                "35 Main Street",
                "May Pen",
                "Clarendon",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbSantaCruz = Branch(
                "Santa Cruz",
                "JMMB",
                "Coke Drive",
                "Santa Cruz",
                "St Elizabeth",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )
            val jmmbFairviewBranch = Branch(
                "Fairview Branch",
                "JMMB",
                "Alice Eldemire Drive",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "",
                "Active"
            )


            branchDao.addBranch(jmmbHO)
            branchDao.addBranch(jmmbKnutsfordBoulevard)
            branchDao.addBranch(jmmbOchoRios)
            branchDao.addBranch(jmmbPortmore)
            branchDao.addBranch(jmmbJunction)
            branchDao.addBranch(jmmbChurchStreet)
            branchDao.addBranch(jmmbHaughtonAvenue)
            branchDao.addBranch(jmmbMandeville)
            branchDao.addBranch(jmmbMayPen)
            branchDao.addBranch(jmmbSantaCruz)
            branchDao.addBranch(jmmbFairviewBranch)
        }


        suspend fun populateCIBC (branchDao: BranchDao) {


            val cibcnewKingston = Branch(
                "New Kingston",
                "CIBC",
                "23-27 Knutsford Blvd",
                "New Kingston",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Paula Brown",
                "Active"
            )
            val cibcHalfWayTree = Branch(
                "Half Way Tree",
                "CIBC",
                "78 Half WayTree Rd",
                "Half Way Tree",
                "St Andrew",
                "",
                0.00,
                0.00,
                " Lorna Burns",
                "Active"
            )
            val cibcKingStreet = Branch(
                "King Street",
                "CIBC",
                "1 King Street",
                "Kingston",
                "Kingston",
                "",
                0.00,
                0.00,
                " Nellie Walker",
                "Active"
            )
            val cibcLiguanea = Branch(
                "Liguanea",
                "CIBC",
                "129 1/2 Old Hope Road ",
                "Liguanea",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Maggie McGann-Williams",
                "Active"
            )
            val cibcMandeville = Branch(
                "Mandeville",
                "CIBC",
                "Park Crescent",
                "Mandeville",
                "Manchester",
                "",
                0.00,
                0.00,
                "Richard Samuels ",
                "Active"
            )
            val cibcManorPark = Branch(
                "Manor Park",
                "CIBC",
                "Constant Spring",
                "Manor Park",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Debbie Henry",
                "Active"
            )
            val cibcMayPen = Branch(
                "May Pen",
                "CIBC",
                "50 Main Street",
                "May Pen",
                "Clarendon",
                "",
                0.00,
                0.00,
                "Winston Lindo",
                "Active"
            )
            val cibcMontegoBay = Branch(
                "Montego Bay",
                "CIBC",
                "Alice Eldemire Drive",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "Horace Watson",
                "Active"
            )
            val cibcOchoRios = Branch(
                "Ocho Rios",
                "CIBC",
                "Ocean Village Shopping Centre",
                "Ocho Rios",
                "St Ann",
                "",
                0.00,
                0.00,
                "Lorna Escoffery",
                "Active"
            )
            val cibcPortAntonio = Branch(
                "Port Antonio",
                "CIBC",
                "",
                "Port Antonio",
                "Portland",
                "",
                0.00,
                0.00,
                "Roxan Bowen",
                "Active"
            )
            val cibcPortmore = Branch(
                "Portmore",
                "CIBC",
                "Corner Old Port Henderson Road & Braeton Parkway",
                "Portmore",
                "St Catherine",
                "",
                0.00,
                0.00,
                "Marion Wilson",
                "Active"
            )
            val cibcnSavannaLaMar = Branch(
                "Savanna La Mar",
                "CIBC",
                "33-35 Beckford Street",
                "Savanna La Mar",
                "Westmoreland",
                "",
                0.00,
                0.00,
                " Wenley Wright",
                "Active"
            )
            val cibcStJamesStreet = Branch(
                "St James Street",
                "CIBC",
                "59 St James Street",
                "Montego Bay",
                "St James",
                "",
                0.00,
                0.00,
                "Horace Watson",
                "Active"
            )
            val cibcTwinGates = Branch(
                "Twin Gates Shopping Centre",
                "CIBC",
                "Twin Gates",
                "Half Way Tree",
                "St Andrew",
                "",
                0.00,
                0.00,
                "Diana Warren",
                "Active"
            )

            branchDao.addBranch(cibcnewKingston)
            branchDao.addBranch(cibcHalfWayTree)
            branchDao.addBranch(cibcKingStreet)
            branchDao.addBranch(cibcLiguanea)
            branchDao.addBranch(cibcMandeville)
            branchDao.addBranch(cibcManorPark)
            branchDao.addBranch(cibcMayPen)
            branchDao.addBranch(cibcMontegoBay)
            branchDao.addBranch(cibcOchoRios)
            branchDao.addBranch(cibcPortAntonio)
            branchDao.addBranch(cibcPortmore)
            branchDao.addBranch(cibcnSavannaLaMar)
            branchDao.addBranch(cibcStJamesStreet)
            branchDao.addBranch(cibcTwinGates)
        }

            //JN


            //VM

        suspend fun populateVMBranch(branchDao: BranchDao){

            val vmDukeStreet = Branch("Duke Street  ", "Victoria Mutual", "8-10 Duke Street", "Kingston", "Kingston", "", 0.00, 0.00, "", "Active")
            val vmFalmouth = Branch("Falmouth", "Victoria Mutual", "15 Market Street", "Falmouth", "Trelawny", "", 0.00, 0.00, "", "Active")
            val vmHWT = Branch("Half Way Tree", "Victoria Mutual", "73-75 Half Way Tree", "Half Way Tree", "St Andrew", "", 0.00, 0.00, "", "Active")
            val vmLiguanea = Branch("Liguanea", "Victoria Mutual", "115 Hope Road", "Liguanea", "St Andrew", "", 0.00, 0.00, "", "Active")
            val vmLinstead = Branch("Linstead", "Victoria Mutual", "110 King Street", "Linstead", "St Catherine", "", 0.00, 0.00, "", "Active")
            val vmMandeville = Branch("Mandeville", "Victoria Mutual", "Manchester Shopping Centre", "Mandeville", "Manchester", "", 0.00, 0.00, "", "Active")
            val vmMayPen = Branch("May Pen", "Victoria Mutual", "40 Main Street", "May Pen", "Clarendon", "", 0.00, 0.00, "", "Active")
            val vmMontegoBay = Branch("Montego Bay", "Victoria Mutual", "30-34 Market Street", "Montego Bay", "St James", "", 0.00, 0.00, "", "Active")
            val vmFairview = Branch("Fairview", "Victoria Mutual", "Fairview Shopping Complex", "Montego Bay", "St James", "", 0.00, 0.00, "", "Active")
            val vmNewKingston = Branch("New Kingston", "Victoria Mutual", "53 Knutsford Boulevard", "New Kingston", "St Andrew", "", 0.00, 0.00, "", "Active")
            val vmOchoRios = Branch("Ocho Rios", "Victoria Mutual", "7 Newlin Street", "Ocho Rios", "St Ann", "", 0.00, 0.00, "", "Active")
            val vmPapine = Branch("Papine", "Victoria Mutual", "237 Old Hope Road", "Papine", "St Andrew", "", 0.00, 0.00, "", "Active")
            val vmPortmore = Branch("Portmore", "Victoria Mutual", "Sea Grape Close", "Portmore", "St Catherine", "", 0.00, 0.00, "", "Active")
            val vmSantaCruz = Branch("Santa Cruz", "Victoria Mutual", "56 Main Street", "Santa Cruz", "St Elizabeth", "", 0.00, 0.00, "", "Active")
            val vmSavannaLaMar = Branch("Savanna La Mar", "Victoria Mutual", "123 Great George Street", "Savanna La Mar", "Westmoreland", "", 0.00, 0.00, "", "Active")
            val vmSpanishTown = Branch("Spanish Town", "Victoria Mutual", "22  Oxford Road", "Spanish Town", "St Catherine", "", 0.00, 0.00, "", "Active")



            branchDao.addBranch(vmDukeStreet)
            branchDao.addBranch(vmFalmouth)
            branchDao.addBranch(vmHWT)
            branchDao.addBranch(vmLiguanea)
            branchDao.addBranch(vmLinstead)
            branchDao.addBranch(vmMandeville)
            branchDao.addBranch(vmMayPen)
            branchDao.addBranch(vmMontegoBay)
            branchDao.addBranch(vmFairview)
            branchDao.addBranch(vmNewKingston)
            branchDao.addBranch(vmOchoRios)
            branchDao.addBranch(vmPapine)
            branchDao.addBranch(vmPortmore)
            branchDao.addBranch(vmSantaCruz)
            branchDao.addBranch(vmSavannaLaMar)
            branchDao.addBranch(vmSpanishTown)


        }





        suspend fun populateJN(branchDao: BranchDao){
            val jnHWT = Branch("Half Way Tree", "JN Bank", "2-4 Constant Spring", "Half Way Tree", "St Andrew", "", 0.00, 0.00, "", "Active")
            val jnNewKingston = Branch("New Kingston", "JN Bank", "10-12 Grenada Crescent", "New Kingston", "St Andrew", "", 0.00, 0.00, "", "Active")
            val jnDowntown = Branch("Downtown Kingston", "JN Bank", "32 1/2 Duke Street", "Kingston", "Kingston", "", 0.00, 0.00, "", "Active")
            val jnMorantBay = Branch("Morant Bay", "JN Bank", "10 Queen Street", "Morant Bay", "St Thomas", "", 0.00, 0.00, "", "Active")
            val jnMandeville = Branch("Mandeville", "JN Bank", "Mandeville Plaza", "Mandeville", "Manchester", "", 0.00, 0.00, "", "Active")
            val jnMayPen = Branch("May Pen", "JN Bank", "45a Main Street", "May Pen", "Clarendon", "", 0.00, 0.00, "", "Active")
            val jnSantaCruz = Branch("Santa Cruz", "JN Bank", "85 Main Street", "Santa Cruz", "St Elizabeth", "", 0.00, 0.00, "", "Active")
            val jnSpanishTown = Branch("Spanish Town", "JN Bank", "26-28 Wellington Street", "Spanish Town", "", "", 0.00, 0.00, "", "Active")
            val jnSav = Branch("Savanna la Mar", "JN Bank", "Hendon Corner", "Savanna la Mar", "Westmoreland", "", 0.00, 0.00, "", "Active")
            val jnBrownsTown = Branch("Brown’s Town", "JN Bank", "Musgrave Square", "Brown’s Town", "St Ann", "", 0.00, 0.00, "", "Active")
            val jnOchoRios = Branch("Ocho Rios", "JN Bank", "Corner of Graham Street & DaCosta Drive", "Ocho Rios", "St Ann", "", 0.00, 0.00, "", "Active")
            val jnPortAntonio = Branch("Port Antonio", "JN Bank", "21 Harbour Street", "Port Antonio", "Portland", "", 0.00, 0.00, "", "Active")
            val jnPortMaria = Branch("Port Maria", "JN Bank", "1 Stennett Street", "Port Maria", "St Mary", "", 0.00, 0.00, "", "Active")
            val jnUWI = Branch("The University of the West Indies", "JN Bank", "Ring Road", "Mona", "St Andrew", "", 0.00, 0.00, "", "Active")
            val jnChristiana = Branch("Christiana", "JN Bank", "Samfo Plaza, Main Street", "Christiana", "Manchester", "", 0.00, 0.00, "", "Active")
            val jnFalmouth = Branch("Falmouth", "JN Bank", "1 Officer’s Alley", "Falmouth", "Trelawny", "", 0.00, 0.00, "", "Active")
            val jnHighgate = Branch("Highgate", "JN Bank", "Main Street", "Highgate", "St Mary", "", 0.00, 0.00, "", "Active")
            val jnJunction = Branch("Junction", "JN Bank", "Shops 7-8, Roye’s Plaza", "Junction", "St Elizabeth", "", 0.00, 0.00, "", "Active")
            val jnLinstead = Branch("Linstead", "JN Bank", "27 King Street", "Linstead", "St Catherine", "", 0.00, 0.00, "", "Active")
            val jnLucea = Branch("Lucea", "JN Bank", "Mosley Drive", "Lucea", "Hanover", "", 0.00, 0.00, "", "Active")
            val jnMontegoBay = Branch("Montego Bay", "JN Bank", "2 Market Street", "Montego Bay", "St James", "", 0.00, 0.00, "", "Active")
            val jnOldHarbour = Branch("Old Harbour", "JN Bank", "8 East Street", "Old Habour", "St Catherine", "", 0.00, 0.00, "", "Active")
            val jnPortmorePines = Branch("Portmore Pines", "JN Bank", "Portmore Pines", "Portmore", "St Catherine", "", 0.00, 0.00, "", "Active")
            val jnStAnnsBay = Branch("St Ann’s Bay", "JN Bank", "10 Bravo Street", "St Ann’s Bay", "St Ann", "", 0.00, 0.00, "", "Active")
            val jnBarbican = Branch("Barbican", "JN Bank", "84 Barbican Road", "Barbican", "St Andrew", "", 0.00, 0.00, "", "Active")

            branchDao.addBranch(jnHWT)
            branchDao.addBranch(jnNewKingston)
            branchDao.addBranch(jnDowntown)
            branchDao.addBranch(jnMorantBay)
            branchDao.addBranch(jnMandeville)
            branchDao.addBranch(jnMayPen)
            branchDao.addBranch(jnSantaCruz)
            branchDao.addBranch(jnSpanishTown)
            branchDao.addBranch(jnSav)
            branchDao.addBranch(jnBrownsTown)
            branchDao.addBranch(jnOchoRios)
            branchDao.addBranch(jnPortAntonio)
            branchDao.addBranch(jnPortMaria)
            branchDao.addBranch(jnUWI)
            branchDao.addBranch(jnChristiana)
            branchDao.addBranch(jnFalmouth)
            branchDao.addBranch(jnHighgate)
            branchDao.addBranch(jnJunction)
            branchDao.addBranch(jnLinstead)
            branchDao.addBranch(jnLucea)
            branchDao.addBranch(jnMontegoBay)
            branchDao.addBranch(jnOldHarbour)
            branchDao.addBranch(jnPortmorePines)
            branchDao.addBranch(jnStAnnsBay)
            branchDao.addBranch(jnBarbican)


        }









        suspend fun populateGlossary(glossaryDao: GlossaryDao) {
            val word1 = Glossary("Amortization" ,"The process of writing off or liquidating an asset or loan periodically on an installment basis.")
            val word2 = Glossary("Accrued liabilities", "Liabilities that represent obligation for certain services for which payments are yet to be made and are indirect sources of financing.")
            val word3 = Glossary( "Annuity","A series of receipts or payments of a fixed amount for a specified number of years. Alternatively, a pattern of cash flows that are equal in each year, that is, equal annual cash flow.")
            val word4 = Glossary("Arbitrage", "Act of buying an asset/security in one market and selling simultaneously in another. This restores equilibrium in markets that are temporarily out of equilibrium.")
            val word5 = Glossary("Assets", "Valuable resources owned by a business, which were acquired at a measurable money cost.")
            val word6 = Glossary("Bad debt", "Debts that are not collectible and therefore, proves to be of no worth to the creditor.")
            val word7 = Glossary("Balance Sheet", "A statement of assets and liabilities at a specified date.")
            val word8 = Glossary("Bonds" , "Long-term debt instruments.")
            val word9 = Glossary("Bonus Share", "Dividend paid in form of equity share and not in cash.")
            val word10 = Glossary("Bridge Loan" , "Refers to short-term loan to fund temporary needs as long as permanent financing is not available.")
            val word11 = Glossary("Business Plan", "A plan that illustrates the current business strata of an organization and its future plans towards the achievement of its organizational objectives.")
            val word12 = Glossary("Capital", "Money put into the business transactions.")

            glossaryDao.insert(word1)
            glossaryDao.insert(word2)
            glossaryDao.insert(word3)
            glossaryDao.insert(word4)
            glossaryDao.insert(word5)
            glossaryDao.insert(word6)
            glossaryDao.insert(word7)
            glossaryDao.insert(word8)
            glossaryDao.insert(word9)
            glossaryDao.insert(word10)
            glossaryDao.insert(word11)
            glossaryDao.insert(word12)



        }

        suspend fun populateTips(tipsDao: TipsDao) {

            val tip1 = Tips(
                0,
                "A good rule of thumb is to spend no more than 25 percent of your monthly household income on all the cars in your household."
            )
            val tip2 = Tips(
                0,
                "Certified pre-owned options can be a great route toward a new car and a cheaper bill, too. These vehicles have to meet a stamp of approval from the manufacturer – the “certified” meaning – so you will get the reassurance of a warranty that you might not get if you have been thinking about how to buy a car from a private seller."
            )
            val tip3 = Tips(
                0,
                "After you’ve set your budget and determined the right type of ownership for your driving habits, start researching the vehicles that have caught your eye."
            )
            val tip4 = Tips(
                0,
                "This new car isn’t going to just sit in your driveway. You’ll be cruising around, and those miles mean more ownership expenses including gas, insurance, repairs and maintenance."
            )
            val tip5 = Tips(
                0,
                "If the dealership is promoting any cash-back deals, these incentives should again be deducted after you negotiate the price."
            )
            val tip6 = Tips(
                0,
                "Do you need a car to get to class or work every day, or just for weekend adventures? What’s the weather like where you live—snowy, rainy or hot? Evaluate your lifestyle and the driving conditions you face most often."
            )
            val tip7 = Tips(
                0,
                "Your credit score helps determine the interest rate you pay on a car loan. Better credit may help get you a more favorable interest rate, which in turn will affect your overall car-buying budget."
            )
            val tip8 = Tips(
                0,
                "Once you’ve identified a few cars that might fit your needs and budget, take each for a test drive to see how you feel in it and how it performs. Try to drive all the cars you’re considering on the same day so you can easily compare them."
            )
            val tip9 = Tips(
                0,
                "While you have to pay more monthly with a shorter loan term, you’ll end up saving money in the long run. Paying off the loan faster means you’ll pay less interest over time."
            )
            val tip10 = Tips(
                0,
                "One of the top tips for anyone purchasing a vehicle is that you should put at least 20% of the total price as a down payment. When you put more money down, this is taken off your initial loan. This will not only decrease the payments, but it may also affect the interest rate."
            )
            val tip11 = Tips(
                0,
                "Other fees can include anything from dealership fees to documentation fees, and there is always sales tax to look out for when purchasing a vehicle. All of these fees and taxes should be paid for in cash rather than rolled into your loan."
            )

            tipsDao.addTip(tip1)
            tipsDao.addTip(tip2)
            tipsDao.addTip(tip3)
            tipsDao.addTip(tip4)
            tipsDao.addTip(tip5)
            tipsDao.addTip(tip6)
            tipsDao.addTip(tip7)
            tipsDao.addTip(tip8)
            tipsDao.addTip(tip9)
            tipsDao.addTip(tip10)
            tipsDao.addTip(tip11)

        }





    }


}




