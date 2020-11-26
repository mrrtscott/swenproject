package com.uwi.loanhub.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.uwi.loanhub.R
import com.uwi.loanhub.models.GlossaryViewModel
import com.uwi.loanhub.models.LoanHubDatabase
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [searchWord.newInstance] factory method to
 * create an instance of this fragment.
 */

lateinit var searchWordButton:Button
lateinit var wordDefinition:TextView
lateinit var searchWordTxt:EditText
lateinit var glossaryModel:GlossaryViewModel



class SearchWord : DialogFragment() {
    private var mListener:SearchWord.OnFragmentInteractionListener? = null
    private val APP_REQUEST_CODE = 1000
    private var inputWordDefinition:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view:View = inflater.inflate(R.layout.wordsearchlayout, container, false)
        searchWordTxt= view.findViewById<EditText>(R.id.inputWordSearch)
        wordDefinition  = view.findViewById<TextView>(R.id.textDefinition)
        searchWordButton = view.findViewById<Button>(R.id.searchWordButton)

        glossaryModel = ViewModelProvider(this).get(GlossaryViewModel::class.java)



        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchWordButton.setOnClickListener {





            mListener!!.onFragmentInteraction(searchWordTxt.text.toString())

            glossaryModel.setArray(arrayListOf(searchWordTxt.text.toString()))
            glossaryModel.glossaryDefinition.observe(viewLifecycleOwner, Observer { definition->
                if (definition.isNotEmpty()){
                    wordDefinition.text = definition[0].definition
                }

            })


        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            if (context is OnFragmentInteractionListener){
                mListener = context

            }



        }catch (e: ClassCastException){

        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener{
        fun onFragmentInteraction(uri: String?)
    }

    companion object {

        fun newInstance(): DialogFragment {
            return DialogFragment()
        }
    }




}