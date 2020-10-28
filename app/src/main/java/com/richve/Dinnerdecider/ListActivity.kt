package com.richve.Dinnerdecider

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.richve.Dinnerdecider.ui.foodList
import com.richve.Dinnerdecider.ui.getDefaults
import com.richve.Dinnerdecider.ui.setDefaults
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*


class ListActivity : AppCompatActivity() {


    lateinit var mAdView2 : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        MobileAds.initialize(this) {}
        mAdView2 = findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
        mAdView2.loadAd(adRequest)

        var myList = ArrayList(getDefaults("maistas", this)?.split(","))
        val myListView = findViewById<ListView>(R.id.listView)

        val myListAdapter = MyListAdapter(this, myList)
        myListView.adapter = myListAdapter

        btn_delete.setOnClickListener{
            for (i in selected.sortedDescending()){

                myList.removeAt(i)
            }
            setDefaults("maistas", myList.joinToString(","), this)
            myListAdapter.notifyDataSetChanged()
            selected.clear()
        }
    }

    override fun onCreateOptionsMenu(list_menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, list_menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intentMainActivity =
                    Intent(applicationContext, MainActivity::class.java).apply {
                    }
                startActivity(intentMainActivity)
            }
            R.id.nav_info -> {
                val intentInfoActivity =
                    Intent(applicationContext, InfoActivity::class.java).apply {
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
