package com.richve.Dinnerdecider

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.richve.Dinnerdecider.ui.getDefaults
import com.richve.Dinnerdecider.ui.setDefaults
import kotlin.collections.ArrayList


class ListActivity : AppCompatActivity() {

    var selected = arrayListOf<Int>()
    lateinit var mAdView2 : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        MobileAds.initialize(this) {}
        mAdView2 = findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
        mAdView2.loadAd(adRequest)

        var myList = ArrayList(getDefaults("maistas", this)?.split(","))
        val arrayAdapter: ArrayAdapter<*>
        var myListView = findViewById<ListView>(R.id.listView)


        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, myList
        )

        myListView?.adapter = arrayAdapter
        myListView?.setOnItemClickListener { _, _, i, _ ->

                myList.removeAt(i)
                setDefaults("maistas", myList.joinToString(","), this)
                arrayAdapter.notifyDataSetChanged()
            }

    }

    override fun onCreateOptionsMenu(list_menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, list_menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intentMainActivity = Intent(applicationContext, MainActivity::class.java).apply {
                }
                startActivity(intentMainActivity)
            }
            R.id.nav_info-> {
                val intentInfoActivity = Intent(applicationContext, InfoActivity::class.java).apply {
                }
                startActivity(intentInfoActivity)
            }
            R.id.nav_exit -> {
                finishAffinity()
            }
        }
        return (false)
    }
}
