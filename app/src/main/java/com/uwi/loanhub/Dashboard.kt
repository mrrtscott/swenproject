package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uwi.loanhub.models.TipsViewModel
import com.uwi.loanhub.models.UserViewModel
import java.util.*

class Dashboard : AppCompatActivity() {

    private lateinit var tipsViewModel: TipsViewModel
    private lateinit var tipsTextView:TextView
    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 10000
    val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)



        tipsViewModel = ViewModelProvider(this).get(TipsViewModel::class.java)
        tipsTextView = findViewById(R.id.dashboardTips)

        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())

            tipsViewModel.allTips.observe(this, Observer { tips ->

                tipsTextView.text = tips[(0..tips.size-1).random()].tip


            })

        }.also { runnable = it }, delay.toLong())


    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }
}