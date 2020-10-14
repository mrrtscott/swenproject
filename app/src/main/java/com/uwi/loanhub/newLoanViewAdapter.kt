package com.uwi.loanhub

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.DecimalFormat


class newLoanViewAdapter(context_c: Context, resource: Int, objects: MutableList<Loans>) :
    ArrayAdapter<Loans>(context_c, resource, objects) {

    private val mycontext:Context  = context_c
    private val loaner:MutableList<Loans> = objects

    @RequiresApi(Build.VERSION_CODES.N)
    fun currencyFormatter(num: String): String? {
        val m = num.toDouble()
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(m)
    }




    @RequiresApi(Build.VERSION_CODES.N)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflator = LayoutInflater.from(mycontext)
        val row = layoutInflator.inflate(com.uwi.loanhub.R.layout.layoutlistviewloan, parent, false)
        row.findViewById<ImageView>(com.uwi.loanhub.R.id.institutionImage).setImageResource(loaner[position].institution.getLogo())
        row.findViewById<TextView>(com.uwi.loanhub.R.id.institutionName).text = loaner[position].institution.getInstitutionName()
        row.findViewById<TextView>(com.uwi.loanhub.R.id.loanName).text = loaner[position].loanName
        row.findViewById<TextView>(com.uwi.loanhub.R.id.loanAmount).text = "Amount: $".plus(currencyFormatter(loaner[position].loanAmount.toString()))
        row.findViewById<TextView>(com.uwi.loanhub.R.id.loanInterestRate).text =  "Interest Rate: ".plus(loaner[position].interestRate.toString()).plus("%")

        return row
    }



}


