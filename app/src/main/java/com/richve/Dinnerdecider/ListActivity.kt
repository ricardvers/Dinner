package com.richve.Dinnerdecider

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


class ListActivity : AppCompatActivity() {

    var selected = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var myList = ArrayList(getDefaults("maistas", this)?.split(","))
        val arrayAdapter: ArrayAdapter<*>
        var myListView = findViewById<ListView>(R.id.listView)


        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_multiple_choice, myList

        )
        myListView?.adapter = arrayAdapter
        myListView?.setOnItemClickListener { _, view, i, _ ->
            (view as Checkable).toggle()
            if (!view.isChecked) {
                view.setBackgroundColor(Color.WHITE)
                selected.remove(i)
            } else {
                view.setBackgroundColor(Color.LTGRAY)
                selected.add(i)
            }

        }
        btn_delete.setOnClickListener {
            Collections.sort(selected, Collections.reverseOrder())
            for (i in selected) myList.removeAt(i)
            setDefaults("maistas", myList.joinToString(","), this)
            arrayAdapter.notifyDataSetChanged()
            selected.clear()

        }
    }

    override fun onCreateOptionsMenu(list_menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, list_menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_one -> {

            }
            R.id.nav_about -> {
                Toast.makeText(applicationContext, "fdfd", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_quit -> {
                Toast.makeText(applicationContext, "fdfd", Toast.LENGTH_SHORT).show()
            }
        }
        return (false)
    }

    fun setChecked(view : View, checked: Boolean = false   ) {
        (view as Checkable).isChecked = checked
    }

    fun delete ( listView: ListView, view: View) {

            (view as Checkable).isChecked = false
    }

}
