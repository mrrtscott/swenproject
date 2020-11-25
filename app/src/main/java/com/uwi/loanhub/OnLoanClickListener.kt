package com.uwi.loanhub

/**
 * A simple interface which will provide the set up to handle the position of a loan when the loan item is clicked
 */
interface OnLoanClickListener {

    /**
     * Function which takes in as input, the position of a loan
     * @param position The position of a loan
     */
    fun onLoanItemClicked(position: Int)
}