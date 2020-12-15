package com.richve.Dinnerdecider

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.richve.Dinnerdecider.ui.getDefaults
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

var isInList = false
const val PREF_KEY = "maistas"

class MainActivity : AppCompatActivity() {
    var foodList = arrayListOf<String>()
    var rolledFood = ""
    lateinit var mAdView3: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        foodList = ArrayList(getDefaults(PREF_KEY, this)?.split(","))

        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        mAdView3 = findViewById(R.id.adView3)
        val adRequest2 = AdRequest.Builder().build()
        mAdView3.loadAd(adRequest2)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        btn_search.setOnClickListener {
            onSearchClick()
        }
        btn_searchRecipe.setOnClickListener {
            onSearchRecipeClick()
        }

        btn_decide.setOnClickListener {
            val random = Random
            val randomFood = random.nextInt(foodList.count())
            tv_result.text = foodList[randomFood]
            btn_search.visibility = View.VISIBLE
            btn_searchRecipe.visibility = View.VISIBLE
            btn_search?.text = "Find restaurants"
            rolledFood = tv_result.text.toString()
            tv_result.text = "You rolled ${tv_result.text}"
            btn_decide.text = "Decide again"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_list -> {
                val intentListActivity =
                    Intent(applicationContext, ListActivity::class.java).apply {
                    }

                startActivity(intentListActivity)
            }
            R.id.nav_exit -> {
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onSearchClick() {
        try {
            val gmmIntentUri =
                Uri.parse("geo:0,0?q=" + Uri.encode(rolledFood + " restaurant"))
            val searchIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            searchIntent.setPackage("com.google.android.apps.maps")
            startActivity(searchIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Ooops, something went wrong...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSearchRecipeClick() {
        try {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            val term = rolledFood + " recipes"
            intent.putExtra(SearchManager.QUERY, term)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Ooops, something went wrong...", Toast.LENGTH_SHORT).show()
        }
    }
}