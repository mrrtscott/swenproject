package com.uwi.loanhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.Loan
import com.uwi.loanhub.models.User

class LoanListAdapter internal constructor(context: Context): RecyclerView.Adapter<LoanListAdapter.LoanViewHolder>()  {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var loan = emptyList<Loan>()

    inner class LoanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val loanNameView: TextView = itemView.findViewById(R.id.loanTitleItemView)
        val institutionNameView: TextView = itemView.findViewById(R.id.institutionItemView)
        val loanDescriptionView: TextView = itemView.findViewById(R.id.descriptionItemView)
        //val institutionImage: ImageView = itemView.findViewById(R.id.imageInstitutionItemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val itemView = inflater.inflate(R.layout.reycleviewitem, parent, false)
        return LoanViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val current = loan[position]
        holder.loanNameView.text = "${current.loanName}"
        holder.institutionNameView.text = "${current.institution}"
        holder.loanDescriptionView.text = "${current.description}"
        //holder.institutionImage.setImageResource()
    }


    internal fun setLoan (loans: List<Loan>){
        this.loan = loans
        notifyDataSetChanged()
    }

    override fun getItemCount() = loan.size
}