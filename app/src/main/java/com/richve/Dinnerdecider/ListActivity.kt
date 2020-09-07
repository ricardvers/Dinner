package com.richve.Dinnerdecider

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.richve.Dinnerdecider.ui.getDefaults
import com.richve.Dinnerdecider.ui.setDefaults
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess


class ListActivity : AppCompatActivity() {

    var selected = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
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
