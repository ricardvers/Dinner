package com.richve.Dinnerdecider

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.richve.Dinnerdecider.ui.getDefaults
import com.richve.Dinnerdecider.ui.setDefaults
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.system.exitProcess



class MainActivity : AppCompatActivity() {

    var foodList = arrayListOf("Chinese", "McDonald's", "Pizza", "Sushi", "Burgers", "Salad", "Portuguese", "Fish")
    var defaultList = foodList.joinToString(",")
    val PREF_NAME = "maisto-listas"
    val PREF_KEY = "maistas"
    var rolledFood = ""
    private val PRIVATE_MODE = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        foodList = ArrayList(getDefaults(PREF_KEY, this)?.split(","))
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)


        btn_search.setOnClickListener {
            onSearchClick()
        }

        btn_decide.setOnClickListener {
            val random = Random
            var randomFood = random.nextInt(foodList.count())
            tv_result.text =  foodList[randomFood]
            btn_search.visibility = View.VISIBLE
            btn_search?.text = "Find ${tv_result.text} restaurants"
            rolledFood = tv_result.text.toString()
            tv_result.text = "You rolled ${tv_result.text}"
        }

        btn_add.setOnClickListener {

                if (et_add.text.toString().isNotBlank()) {
                    val newFood = et_add.text.toString()
                    foodList.add(newFood)
                    setDefaults(PREF_KEY, foodList.joinToString(","), this)
                    Toast.makeText(this, "$newFood was added to your list!", Toast.LENGTH_SHORT)
                        .show()
                    et_add.text.clear()

                } else Toast.makeText(this, "Please write something first", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {

            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.nav_list -> {
                    val intentListActivity = Intent(applicationContext, ListActivity::class.java).apply {
                    }
                        startActivity(intentListActivity)
                    }
                R.id.nav_info -> {
                    val intentInfoActivity = Intent(applicationContext, InfoActivity::class.java).apply {
                    }
                    startActivity(intentInfoActivity)
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
}