package com.uwi.loanhub.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.uwi.loanhub.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [searchWord.newInstance] factory method to
 * create an instance of this fragment.
 */
class searchWord : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view:View = inflater.inflate(R.layout.wordsearchlayout, container, false)
        val searchWord:EditText= view.findViewById<EditText>(R.id.inputWordSearch)
        val wordDefinition:TextView  = view.findViewById<TextView>(R.id.textDefinition)
        val cancelButton = view.findViewById<Button>(R.id.searchWordButtonCanel)


        cancelButton.setOnClickListener { dialog!!.dismiss() }



        return view
    }


    companion object {

        fun newInstance(): DialogFragment {
            return DialogFragment()
        }
    }


}