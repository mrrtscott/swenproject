package com.uwi.loanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class TermsOfUse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_use)


        val web =findViewById<WebView>(R.id.termsWebView)
        web.loadUrl("file:///android_asset/terms.html")
    }
}