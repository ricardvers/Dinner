package com.richve.Dinnerdecider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlin.system.exitProcess

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
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
