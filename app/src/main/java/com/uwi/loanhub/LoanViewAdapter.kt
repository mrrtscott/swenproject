package com.uwi.loanhub

import android.content.Context
import android.icu.text.DecimalFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class LoanViewAdapter(loanListModel: LoanListViewModel, c_context: Context): BaseAdapter() {

    private val loansModel: LoanListViewModel
    private val context: Context

    init {

        loansModel = loanListModel
        context = c_context

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun currencyFormatter(num: String): String? {
        val m = num.toDouble()
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(m)
    }

    override fun getCount(): Int {
        return loansModel.loan.count()

    }



    override fun getItem(position: Int): Any {

        return loansModel.loan[position]

    }

    override fun getItemId(position: Int): Long {
        return loansModel.loan[position].hashCode().toLong()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflator = LayoutInflater.from(context)
        val row = layoutInflator.inflate(R.layout.layoutlistviewloan, parent, false)
        row.findViewById<ImageView>(R.id.institutionImage).setImageResource(loansModel.loan[position].institution.getLogo())
        row.findViewById<TextView>(R.id.institutionName).text = loansModel.loan[position].institution.getInstitutionName()
        row.findViewById<TextView>(R.id.loanName).text = loansModel.loan[position].loanName
        row.findViewById<TextView>(R.id.loanAmount).text = "Amount: $".plus(currencyFormatter(loansModel.loan[position].loanAmount.toString()))
        row.findViewById<TextView>(R.id.loanInterestRate).text =  "Interest Rate: ".plus(loansModel.loan[position].interestRate.toString()).plus("%")

        return row

    }
}