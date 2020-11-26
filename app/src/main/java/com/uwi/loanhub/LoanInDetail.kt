package com.uwi.loanhub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButtonToggleGroup
import com.uwi.loanhub.AppConstants.CITY
import com.uwi.loanhub.AppConstants.INSTITUTION
import com.uwi.loanhub.AppConstants.LOANID
import com.uwi.loanhub.AppConstants.PARISH
import com.uwi.loanhub.AppConstants.USERNAME
import com.uwi.loanhub.fragments.SearchWord
import com.uwi.loanhub.models.*
import kotlinx.android.synthetic.main.activity_loan_in_detail.*

/**
 * This is used to mange the details related to each loan
 */

class LoanInDetail : AppCompatActivity(), SearchWord.OnFragmentInteractionListener {

    private lateinit var functions: Functions
    private lateinit var loanInstitutionViewModel: LoanInstitutionViewModel
    private lateinit var loanLikesViewModel: LoanLikesViewModel
    private lateinit var loanRatingViewModel:LoanRatingViewModel

    private lateinit var searchWordFragment:SearchWord


    private lateinit var requirementsButton: Button
    private lateinit var likeLoanButton:Button
    private lateinit var institutionButton:Button
    private lateinit var likeButtonGroup: MaterialButtonToggleGroup
    private var sentArrayListLoanLikes:ArrayList<String> = arrayListOf()
    private var sentArrayLoanInstitution:ArrayList<String> = arrayListOf()


    var loanIDForLikes: Int = 0
    var loanNameLikes:String = ""
    var loanInstitutionLikes: String = ""
    var city: String = ""
    var parish: String = ""







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_in_detail)


        functions = Functions()

        val manager: FragmentManager = supportFragmentManager
        searchWordFragment = SearchWord()




        //Receiving data from previous activity
        val previousIntent = intent
        val LoanID = previousIntent.getIntExtra("LOANID", 0)
        val username = previousIntent.getStringExtra("USERNAME")

        city = previousIntent.getStringExtra("CITY")
        parish = previousIntent.getStringExtra("PARISH")


        requirementsButton = findViewById(R.id.requirementsButton)
        likeLoanButton = findViewById(R.id.likedLoans)
        institutionButton = findViewById(R.id.institutionDetail)
        likeButtonGroup = findViewById(R.id.likeToggleGroup)

        loanInstitutionViewModel = ViewModelProvider(this).get(LoanInstitutionViewModel::class.java)
        loanLikesViewModel = ViewModelProvider(this).get(LoanLikesViewModel::class.java)
        loanRatingViewModel = ViewModelProvider(this).get(LoanRatingViewModel::class.java)
        sentArrayLoanInstitution.add(LoanID.toString())
        sentArrayLoanInstitution.add(username)

        loanInstitutionViewModel.setArray(sentArrayLoanInstitution)


        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener { view ->

            searchWordFragment.show(manager, "Search")




            //Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                //.setAction("Action", null)
                //.show()
        }



        loanInstitutionViewModel.specificLoanDetail.observe(this, Observer { loans ->

            val institutionName = findViewById<TextView>(R.id.loanDetailLoanInstitutionName)
            val institutionLogo = findViewById<ImageView>(R.id.loanDetailLoanInstitutionLogo)
            val institutionSlogan = findViewById<TextView>(R.id.loanDetailInstitutionSlogan)
            val institutionWebsite = findViewById<TextView>(R.id.loanDetailInstitutionWebsite)
            val loanName = findViewById<TextView>(R.id.loanDetailLoanName)
            val loanEmail = findViewById<TextView>(R.id.loanDetailInstitutionEmail)
            val loanPhone = findViewById<TextView>(R.id.loanDetailInstitutionPhone)
            val loanDetails = findViewById<TextView>(R.id.loanDetailLoanDescription)
            val loanAmount = findViewById<TextView>(R.id.loanDetailLoanMaximumAmount)
            val loanInterestRate = findViewById<TextView>(R.id.loanDetailLoanInterestRate)
            val loanRepay = findViewById<TextView>(R.id.loanDetailLoanRepayment)




            institutionName.text = loans[0].institution
            institutionLogo.setImageResource(loans.get(0).logo!!.toInt())
            loanName.text = loans.get(0).loanName
            loanEmail.text = loans.get(0).email
            loanPhone.text = loans.get(0).phone
            institutionWebsite.text = loans.get(0).website
            loanAmount.text =
                "$".plus(functions.currencyFormatter(loans.get(0).loanAmount.toString()))
            loanDetails.text = loans.get(0).description
            loanInterestRate.text = getString(R.string.interestRateDetails).plus(
                loans.get(0).interestRate.toString().plus(
                    "%"
                )
            )
            loanRepay.text =
                getString(R.string.termsToRepayLoanInDetail).plus(loans.get(0).termsRepay)
            institutionSlogan.text = loans.get(0).slogan




            sentArrayListLoanLikes.clear()
            sentArrayListLoanLikes = arrayListOf(
                loans.get(0).id.toString(), loans.get(0).loanName.toString(), loans.get(
                    0
                ).institution.toString(), username
            )
            loanLikesViewModel.setArrayInput(sentArrayListLoanLikes)

            likeLoanButton.setOnClickListener {
                val intent = Intent(this, LoanInterest::class.java)
                intent.putExtra(USERNAME, username)
                intent.putExtra(LOANID, loans.get(0).id)
                startActivity(intent)
            }

            institutionButton.setOnClickListener {
                val intent = Intent(this, InstitutionActivity::class.java)
                intent.putExtra(INSTITUTION, loans[0].institution)
                intent.putExtra(CITY, city)
                intent.putExtra(PARISH, parish)
                startActivity(intent)
            }


            //For the likes

            loanLikesViewModel.allLoansLikes.observe(this, Observer { likes ->

                if (likes.size == 1) {

                    if (likes.get(0).value == 1) {
                        likeToggleGroup.check(R.id.likeButton)
                    } else {

                        likeToggleGroup.check(R.id.dislikeButton)

                    }

                }

            })


            val rating = resources.getStringArray(R.array.rating)
            val autocompleteRating =
                findViewById<AutoCompleteTextView>(R.id.autoCompleteLoanInDetailRating)
            val ratingOutput = ArrayAdapter(this, R.layout.rating, rating)
            ratingOutput.setDropDownViewResource(android.R.layout.simple_list_item_1)
            autocompleteRating!!.setAdapter(ratingOutput)





            autocompleteRating.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position).toString()

                    var loanRating = LoanRating(
                        loans.get(0).id,
                        username.toString(),
                        selectedItem.toInt()
                    )
                    loanRatingViewModel.insert(loanRating)


                }

            var loanRatingArrayList: ArrayList<String> = arrayListOf(
                username.toString(),
                loans.get(0).id.toString()
            )
            loanRatingViewModel.setArrayInput(loanRatingArrayList)


            loanRatingViewModel.allLoanRating.observe(this, Observer { rating ->

                if (rating.size == 1) {
                    autocompleteRating.setText(rating.get(0).rating.toString(), false)
                }


            })

















            requirementsButton.setOnClickListener {
                val intent = Intent(this, RequirementActivity::class.java)
                intent.putExtra(INSTITUTION, loans[0].institution)
                startActivity(intent)
            }


            likeButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->


                if (isChecked && checkedId == R.id.likeButton) {

                    var loanLike = LoanLikes(
                        username,
                        loans.get(0).id,
                        loans.get(0).institution.toString().trim(),
                        loans.get(
                            0
                        ).loanName.toString().trim(),
                        1
                    )
                    loanLikesViewModel.insert(loanLike)


                } else if (isChecked && checkedId == R.id.dislikeButton) {

                    var loanLike = LoanLikes(
                        username,
                        loans.get(0).id,
                        loans.get(0).institution.toString().trim(),
                        loans.get(
                            0
                        ).loanName.toString().trim(),
                        0
                    )
                    loanLikesViewModel.insert(loanLike)

                }


            }


        })




















    }



    override fun onFragmentInteraction(uri: String?) {
        println(uri)







    }


}