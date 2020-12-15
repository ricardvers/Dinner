package com.richve.Dinnerdecider

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.list_item.view.*

var selected = arrayListOf<Int>()

class MyListAdapter(private val context: Activity, private val myList: ArrayList<String>) :
    ArrayAdapter<String>(context, R.layout.list_item, myList) {


    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item, null, true)

        val titleText = rowView.findViewById(R.id.label) as TextView
        val checkBox = rowView.findViewById(R.id.checkBox) as CheckBox

        checkBox.setOnClickListener {
            if (selected.contains(position)) {
                selected.remove(position)
            } else
                selected.add(position)
        }


        titleText.text = myList[position]
        return rowView
    }


}