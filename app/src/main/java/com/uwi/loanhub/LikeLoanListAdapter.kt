package com.uwi.loanhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitution

class LikeLoanListAdapter internal constructor(context: Context, private val loanClickListener: OnLoanClickListener) : RecyclerView.Adapter<LikeLoanListAdapter.LikeLoanViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var loanLike = emptyList<LoanInstitution>()

    inner class LikeLoanViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val loanNameView: TextView = itemView.findViewById(R.id.loanOfInterestLoanName)
        val institutionNameView: TextView = itemView.findViewById(R.id.loanOfInterestInstitution)
        val loanDescriptionView: TextView = itemView.findViewById(R.id.loanOfInterestDescription)
        val institutionImage: ImageView = itemView.findViewById(R.id.loanOfInterestPhoto)
        var shareButton: Button = itemView.findViewById(R.id.loanOfInterestShareButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeLoanViewHolder  {
        val itemView = inflater.inflate(R.layout.recycleviewiteminterest, parent, false)
        return LikeLoanViewHolder(itemView)
    }


    override fun getItemCount() = loanLike.size

    override fun onBindViewHolder(holder: LikeLoanViewHolder, position: Int) {
        val current = loanLike[position]
        holder.loanNameView.text = "${current.loanName}"
        holder.institutionNameView.text = "${current.institution}"
        holder.loanDescriptionView.text = "${current.description}"
        holder.institutionImage.setImageResource("${current.logo}".toInt())
        holder.shareButton = holder.itemView.findViewById(R.id.loanOfInterestShareButton)
        holder.shareButton.setOnClickListener { loanClickListener.onLoanItemClicked(position) }
    }

    internal fun setLoan (loans: List<LoanInstitution>){
        this.loanLike = loans
        notifyDataSetChanged()
    }

}
