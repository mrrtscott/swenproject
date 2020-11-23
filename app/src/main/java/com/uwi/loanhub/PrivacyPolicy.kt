package com.uwi.loanhub

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import java.io.InputStream
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class PrivacyPolicy : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        drawerLayout = findViewById(R.id.drawerLayout_privacy)
        navView = findViewById(R.id.navView)
        toggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener(this)

        val web =findViewById<WebView>(R.id.webView)
        web.loadUrl("file:///android_asset/privacypolicy.html")
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            Toast.makeText(this, "hello1", Toast.LENGTH_SHORT).show()
            return true
        }


        return super.onOptionsItemSelected(item)
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){


            R.id.share ->{

                val text: String = applicationContext.assets.open("privacypolicy.html").bufferedReader().use{
                    it.readText()
                }


                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/html"
                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    Html.fromHtml(text)
                )
                startActivity(Intent.createChooser(sharingIntent, "Share using"))

            }


            R.id.logout -> {
                var intent: Intent = Intent(this,LoginActivityNew::class.java)
                startActivity(intent)
            }
            else -> Log.d(this.toString(),item.itemId.toString())



        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}