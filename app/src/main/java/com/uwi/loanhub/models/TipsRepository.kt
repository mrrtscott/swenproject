package com.uwi.loanhub.models

import androidx.lifecycle.LiveData

class TipsRepository(private val inputTipsDao: TipsDao) {

    val allTips:LiveData<List<Tips>> = inputTipsDao.getTips()

    suspend fun addTip (inputTips: Tips){
        inputTipsDao.addTip(inputTips)
    }
}