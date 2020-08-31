package com.richve.Dinnerdecider

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.system.Os.remove
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONStringer
import kotlin.random.Random
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    public var foodList = arrayListOf("Chinese", "McDonald's", "Pizza", "Sushi")
    public var defaultList = foodList.joinToString(",")
    private val PREF_NAME = "maisto-listas"
    private val PREF_KEY = "maistas"
    private val PRIVATE_MODE = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        foodList = ArrayList(sharedPref.getString(PREF_KEY, defaultList)?.split(","))
        setContentView(R.layout.activity_main)


        btn_search.setOnClickListener {
            onSearchClick()
        }

        btn_decide.setOnClickListener {
            val random = Random
            var randomFood = random.nextInt(foodList.count())
            val food = foodList[randomFood]
            TextView.text =  foodList[randomFood].toString()
            btn_search.visibility = View.VISIBLE


        }
            btn_add.setOnClickListener {
                if (et_add.text.toString().isNotBlank()) {
                    val newFood = et_add.text.toString()
                    foodList.add(newFood)

                    with (sharedPref.edit()) {
                        putString(PREF_KEY, foodList.joinToString(","))
                        commit()
                    }

                    Toast.makeText(this, "$newFood was added to your list!", Toast.LENGTH_SHORT)
                        .show()
                    et_add.text.clear()



                } else Toast.makeText(this, "Please write something first", Toast.LENGTH_SHORT)
                    .show()
            }

            btn_list.setOnClickListener {
                val intent = Intent(this, ListActivity::class.java).apply {
                    putExtra("Listas", foodList);
                    startActivity(this)
                }

            }
        }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {

            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {

                R.id.nav_about -> Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
                R.id.nav_quit -> {
                    this@MainActivity.finish()
                    exitProcess(0)
                }
            }

            return super.onOptionsItemSelected(item)
        }

         private fun onSearchClick() {
            try {
                val term: String = TextView.text.toString()
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=" + Uri.encode(term + " restaurant"))
                val searchIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                searchIntent.setPackage("com.google.android.apps.maps")
                startActivity(searchIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "Ooops, something went wrong...", Toast.LENGTH_SHORT).show()
            }
        }
}