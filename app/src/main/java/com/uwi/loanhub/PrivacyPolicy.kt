package com.uwi.loanhub

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream


class PrivacyPolicy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        val web =findViewById<WebView>(R.id.webView)
        web.loadUrl("file:///android_asset/privacypolicy.html")
        }
}