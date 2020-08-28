package com.richve.Dinnerdecider

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*
import kotlin.system.exitProcess

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val myList =
            intent.getSerializableExtra("Listas") as List<String?>
        val arrayAdapter: ArrayAdapter<*>
        var myListView = findViewById<ListView>(R.id.listView)
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, myList

        )
        myListView.adapter = arrayAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_about -> onBackPressed()
            R.id.nav_quit -> {
                this@ListActivity.finish()

                exitProcess(0)
            }
        }
        return (false)
    }
}