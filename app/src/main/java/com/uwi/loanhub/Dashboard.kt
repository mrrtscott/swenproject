package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.TipsViewModel
import com.uwi.loanhub.models.UserViewModel

class Dashboard : AppCompatActivity() {

    private lateinit var tipsViewModel: TipsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tipsViewModel = ViewModelProvider(this).get(TipsViewModel::class.java)

        tipsViewModel.allTips.observe(this, Observer { tips ->

        })
    }
}