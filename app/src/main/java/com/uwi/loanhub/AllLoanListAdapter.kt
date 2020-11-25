package com.uwi.loanhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.LoanInstitution
import com.uwi.loanhub.models.User

/**
*This class is responsible for the taking raw loans data and structure it for the loans recycle view
 *  @param context The context of the application
 *  @property loanClickListener a listener for when the loan is clicked
 *  @property compareItemListener a listener for when loans are checked and unchecked
 */
class AllLoanListAdapter internal constructor(context: Context, private val loanClickListener: OnLoanClickListener, private val compareItemListener: OnCompareLoanClickListener): RecyclerView.Adapter<AllLoanListAdapter.LoanViewHolder>()  {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var loanInstitution = emptyList<LoanInstitution>()

    inner class LoanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val loanNameView: TextView = itemView.findViewById(R.id.loanTitleItemView)
        val institutionNameView: TextView = itemView.findViewById(R.id.institutionItemView)
        val loanDescriptionView: TextView = itemView.findViewById(R.id.descriptionItemView)
        val institutionImage: ImageView = itemView.findViewById(R.id.imageInstitutionItemView)
        val checkBoxLoanList: CheckBox = itemView.findViewById(R.id.checkBoxLoanList)
    }

    /**
     * The primary function of this method is to inflate the recycle item view which holds a single loan structure
     * @param parent
     * @param viewType
     * @return  The entire view which has been inflated
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val itemView = inflater.inflate(R.layout.reycleviewitem, parent, false)
        return LoanViewHolder(itemView)
    }


    /**
     * This function is responsible for setting the values in the holder to its respective view
     * @param holder takes an inner class of type LoanViewHolder wis used to initialise the various views
     * @param position This take the position of the item in the holder.
     */
    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val current = loanInstitution[position]
        holder.loanNameView.text = "${current.loanName}"
        holder.institutionNameView.text = "${current.institution}"
        holder.loanDescriptionView.text = "${current.description}"
        holder.institutionImage.setImageResource("${current.logo}".toInt())

        //This here returns the position of the loan which was clicked
        holder.itemView.setOnClickListener {
            loanClickListener.onLoanItemClicked(position)
        }

        //This method returns the action and the position of the item which the action was performed on
        holder.checkBoxLoanList.setOnCheckedChangeListener ({buttonView, isChecked ->

            if (isChecked) {
                compareItemListener.onLoanCompareItemClicked(position, "CHECKED")
            } else {
                compareItemListener.onLoanCompareItemClicked(position, "UNCHECKED")
            }


        })


    }

    /**
     * Sets the internal list to a list of loans which have been passed by the database
     * @property loans input loans of type LoanInstitution
     */
    internal fun setLoan (loans: List<LoanInstitution>){
        this.loanInstitution = loans
        notifyDataSetChanged()
    }

    /**
     * This function is responsible for getting the number of loans which have been loaded
     * @return An value of type integer which is tht total number of loan which have been loaded
     */
    override fun getItemCount() = loanInstitution.size
}