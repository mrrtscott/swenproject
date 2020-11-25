package com.uwi.loanhub

/**
 *  A simple interface which will provide the set up to handle the position on a clicked loan for comparison and also the action which was performed
 */
interface OnCompareLoanClickListener {
    /**
     * Function which takes in as input, the position of a loan and the action which has been taken
     * @param position The position of a loan
     * @param action The action which has been taken whether checked or unchecked
     *
     */
    fun onLoanCompareItemClicked(position: Int, action: String)
}