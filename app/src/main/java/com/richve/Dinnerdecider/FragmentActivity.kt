package com.richve.Dinnerdecider

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.richve.Dinnerdecider.ui.SliderFragment
import kotlinx.android.synthetic.main.fragment_activity.*


class FragmentActivity : AppCompatActivity() {

    private val fragment1 = SliderFragment()
    private val fragment2 = SliderFragment()
    private val fragment3 = SliderFragment()
    private val fragment4 = SliderFragment()
    private val fragment5 = SliderFragment()

    lateinit var activity : Activity
    lateinit var preference : SharedPreferences
    private val introPreference = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)
        activity = this
        preference = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)

        if(!preference.getBoolean(introPreference,true)){
            startActivity(Intent(activity,MainActivity::class.java))
            finish()
        }

        fragment1.setTitle(getString(R.string.Intro1))
        fragment2.setTitle(getString(R.string.Intro2))
        fragment3.setTitle(getString(R.string.Intro3))
        fragment4.setTitle(getString(R.string.Intro4))
        fragment5.setTitle(getString(R.string.Intro5))

        val adapter = MyPagerAdapter(supportFragmentManager)
        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)
        adapter.list.add(fragment4)
        adapter.list.add(fragment5)

        view_pager.adapter = adapter

        btn_next.setOnClickListener {view_pager.currentItem++}
        btn_back.setOnClickListener {view_pager.currentItem--}

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                if (view_pager.currentItem == 0) {
                    btn_back.visibility = View.INVISIBLE
                } else {
                    btn_back.visibility = View.VISIBLE
                }

                if (position == adapter.list.size - 1) {
                    btn_next.setOnClickListener {
                        goToDashboard()
                    }
                } else {

                    btn_next.setOnClickListener {
                        view_pager.currentItem++
                    }
                }

                when (view_pager.currentItem) {
                    0 -> {
                        indicator1.setTextColor(Color.BLACK)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)
                        indicator4.setTextColor(Color.GRAY)
                        indicator5.setTextColor(Color.GRAY)
                    }
                    1 -> {
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.BLACK)
                        indicator3.setTextColor(Color.GRAY)
                        indicator4.setTextColor(Color.GRAY)
                        indicator5.setTextColor(Color.GRAY)
                        btn_back.setImageDrawable(getDrawable(R.drawable.ic_baseline_first_page_24))
                    }
                    2 -> {
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.BLACK)
                        indicator4.setTextColor(Color.GRAY)
                        indicator5.setTextColor(Color.GRAY)
                        btn_back.setImageDrawable(getDrawable(R.drawable.ic_baseline_keyboard_arrow_left_24))
                    }
                    3 -> {
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)
                        indicator4.setTextColor(Color.BLACK)
                        indicator5.setTextColor(Color.GRAY)
                        btn_next.setImageDrawable(getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24))
                    }
                    4 -> {
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)
                        indicator4.setTextColor(Color.GRAY)
                        indicator5.setTextColor(Color.BLACK)
                        btn_next.setImageDrawable(getDrawable(R.drawable.ic_baseline_last_page_24))
                    }
                }

            }

        })

    }

    fun goToDashboard(){
        startActivity(Intent(activity, ListActivity::class.java))
        finish()
        val editor = preference.edit()
        editor.putBoolean(introPreference, false)
        editor.apply()
    }

    class MyPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(
        manager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ){

        val list : MutableList<Fragment> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return list[position]
        }

        override fun getCount(): Int {
            return list.size
        }
    }
}