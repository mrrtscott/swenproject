package com.uwi.loanhub.models

import androidx.lifecycle.LiveData


/**
 * The tips repository class which manages the data for the tips
 */
class TipsRepository(private val inputTipsDao: TipsDao) {

    val allTips:LiveData<List<Tips>> = inputTipsDao.getTips()

    /**
     * A method to add a tip to the database
     * @param inputTips Tip
     */
    suspend fun addTip (inputTips: Tips){
        inputTipsDao.addTip(inputTips)
    }
}