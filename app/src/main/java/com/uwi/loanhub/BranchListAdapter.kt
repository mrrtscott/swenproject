package com.uwi.loanhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uwi.loanhub.models.Branch

/**
 *
 */
class BranchListAdapter  internal constructor(context: Context): RecyclerView.Adapter<BranchListAdapter.BranchViewHolder>()  {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var branches = emptyList<Branch>()

    inner class BranchViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val branchName: TextView = itemView.findViewById(R.id.branchName)
        val branchManager: TextView = itemView.findViewById(R.id.branchManager)
        val streetBranch: TextView = itemView.findViewById(R.id.streetNameBranch)
        val cityBranch: TextView = itemView.findViewById(R.id.cityBranch)
        val parishBranch: TextView = itemView.findViewById(R.id.parishBranch)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BranchListAdapter.BranchViewHolder {
        val itemView = inflater.inflate(R.layout.branchrecycleitem, parent, false)
        return BranchViewHolder(itemView)

    }

    override fun getItemCount() = branches.size

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val current = branches[position]
        holder.branchName.text = "${current.name}"
        holder.branchManager.text = "${current.manager}"
        holder.streetBranch.text = "${current.street}"
        holder.cityBranch.text = "${current.city}"
        holder.parishBranch.text = "${current.parish}"
    }


    internal fun setBranch (branch: List<Branch>){
        this.branches = branch
        notifyDataSetChanged()
    }
}