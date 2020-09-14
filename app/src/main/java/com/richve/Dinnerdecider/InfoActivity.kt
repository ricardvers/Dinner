package com.richve.Dinnerdecider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_info.*
import com.google.android.gms.ads.AdView

class InfoActivity : AppCompatActivity() {
    lateinit var mAdView2 : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)


        MobileAds.initialize(this) {}
        mAdView2 = findViewById(R.id.adView2)
        val adRequest2 = AdRequest.Builder().build()
        mAdView2.loadAd(adRequest2)
        tv_instructions.setMovementMethod(ScrollingMovementMethod())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                val intentMainActivity = Intent(applicationContext, MainActivity::class.java).apply {
                }
                startActivity(intentMainActivity)
            }
            R.id.nav_list -> {
                val intentListActivity = Intent(applicationContext, ListActivity::class.java).apply {
                }
                startActivity(intentListActivity)
            }
            R.id.nav_exit -> {
                finishAffinity()
            }
        }
        return (true)
    }
}