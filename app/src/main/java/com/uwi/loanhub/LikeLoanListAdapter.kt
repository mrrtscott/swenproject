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


/**
 * This class is responsible for the taking raw loans likes data and structure it for the loans likes recycle view
 * @param context Context of the application
 * @property loanClickListener a listener to determine which loan has been liked
 *
 */
class LikeLoanListAdapter internal constructor(context: Context, private val loanClickListener: OnLoanClickListener) : RecyclerView.Adapter<LikeLoanListAdapter.LikeLoanViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var loanLike = emptyList<LoanInstitution>()

    /**
     * An internal class which initialises the various views required
     */
    inner class LikeLoanViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val loanNameView: TextView = itemView.findViewById(R.id.loanOfInterestLoanName)
        val institutionNameView: TextView = itemView.findViewById(R.id.loanOfInterestInstitution)
        val loanDescriptionView: TextView = itemView.findViewById(R.id.loanOfInterestDescription)
        val institutionImage: ImageView = itemView.findViewById(R.id.loanOfInterestPhoto)
        var shareButton: Button = itemView.findViewById(R.id.loanOfInterestShareButton)

    }

    /**
     * The primary function of this method is to inflate the recycle item view which holds a loan like structure
     * @param parent
     * @param viewType
     * @return The entire view which has been inflated
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeLoanViewHolder  {
        val itemView = inflater.inflate(R.layout.recycleviewiteminterest, parent, false)
        return LikeLoanViewHolder(itemView)
    }

    /**
     * This function is responsible for getting the number of loans which have been loaded
     * @return An value of type integer which is tht total number of loan which have been loaded
     */
    override fun getItemCount() = loanLike.size



    /**
     * This function is responsible for setting the values in the holder to its respective view
     * @param holder takes an inner class of type LoanViewHolder wis used to initialise the various views
     * @param position This take the position of the item in the holder.
     */
    override fun onBindViewHolder(holder: LikeLoanViewHolder, position: Int) {
        val current = loanLike[position]
        holder.loanNameView.text = "${current.loanName}"
        holder.institutionNameView.text = "${current.institution}"
        holder.loanDescriptionView.text = "${current.description}"
        holder.institutionImage.setImageResource("${current.logo}".toInt())
        holder.shareButton = holder.itemView.findViewById(R.id.loanOfInterestShareButton)
        holder.shareButton.setOnClickListener { loanClickListener.onLoanItemClicked(position) }
    }

    /**
     * Sets the internal list to a list of loans which have been passed by the database
     * @property loans input loans of type LoanInstitution
     */
    internal fun setLoan (loans: List<LoanInstitution>){
        this.loanLike = loans
        notifyDataSetChanged()
    }

}
