package com.richve.Dinnerdecider

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.richve.Dinnerdecider.ui.getDefaults
import com.richve.Dinnerdecider.ui.setDefaults
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var mAdView2: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        MobileAds.initialize(this) {}
        mAdView2 = findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
        mAdView2.loadAd(adRequest)

        val myList = ArrayList(getDefaults("maistas", this)?.split(","))
        val myListView = findViewById<ListView>(R.id.listView)

        val myListAdapter = MyListAdapter(this, myList)
        myListView.adapter = myListAdapter

        btn_delete.setOnClickListener {
            if (selected.isEmpty()) {
                Toast.makeText(this, "No items are selected", Toast.LENGTH_SHORT).show()
            } else {
                for (i in selected.sortedDescending()) {
                    myList.removeAt(i)
                }
                setDefaults(PREF_KEY, myList.joinToString(","), this)
                myListAdapter.notifyDataSetChanged()
                selected.clear()
                Toast.makeText(this, "Selected items successfully deleted!", Toast.LENGTH_SHORT)
                    .show()
            }
            hideKeyboard()
        }

        btn_add2.setOnClickListener {
            hideKeyboard()
            if (et_add2.text.toString().isNotBlank()) {

                for (word in myList) {
                    if (et_add2.text.toString().equals(word, true)) {
                        isInList = true
                        break
                    } else isInList = false
                }
                if (isInList) {
                    Toast.makeText(
                        this,
                        "This item is already in your list!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {

                    val newFood = et_add2.text.toString()
                    myList.add(newFood)
                    setDefaults(PREF_KEY, myList.joinToString(","), this)
                    myListAdapter.notifyDataSetChanged()
                    Toast.makeText(
                        this,
                        "$newFood was added to your list!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    et_add2.text.clear()

                }
            } else Toast.makeText(this, "Please write something first", Toast.LENGTH_SHORT)
                .show()
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
            R.id.nav_exit -> {
                finishAffinity()
            }
        }
        return (false)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
        et_add2.clearFocus()
    }
}
