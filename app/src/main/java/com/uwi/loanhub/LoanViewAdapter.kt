package com.uwi.loanhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class LoanViewAdapter(loanListModel: LoanListViewModel, c_context: Context): BaseAdapter() {

    private val loansModel: LoanListViewModel
    private val context: Context

    init {

        loansModel = loanListModel
        context = c_context

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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflator = LayoutInflater.from(context)
        val row = layoutInflator.inflate(R.layout.layoutlistviewloan, parent, false)
        row.findViewById<ImageView>(R.id.institutionImage).setImageResource(loansModel.loan[position].institution.getLogo())
        row.findViewById<TextView>(R.id.institutionName).text = loansModel.loan[position].institution.getinstituionName()
        row.findViewById<TextView>(R.id.loanName).text = loansModel.loan[position].loanName
        row.findViewById<TextView>(R.id.loanAmount).text = loansModel.loan[position].loanAmount.toString()
        row.findViewById<TextView>(R.id.loanInterestRate).text = loansModel.loan[position].interestRate.toString()

        return row

    }
}