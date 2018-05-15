package com.example.boilerplateapplication.scenes.main.controls

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.boilerplateapplication.R
import com.example.boilerplateapplication.common.activities.BaseHomeFragment
import com.example.boilerplateapplication.common.extensions.getString
import com.example.boilerplateapplication.scenes.main.common.MainModels
import com.example.boilerplateapplication.scenes.main.common.MainModels.OptionType.*
import com.example.boilerplateapplication.scenes.main.common.NavigationPagerAdapter
import kotlinx.android.synthetic.main.tab_layout.view.*

/**
 * Created by ahmedsaad on 2018-01-18.
 * Copyright © 2017. All rights reserved.
 */
class MainPagerAdapter(val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm), NavigationPagerAdapter {

    val data = makeNavigationViewModel()
    private val homeFragment = BaseHomeFragment()

    companion object {
        fun makeNavigationViewModel(): ArrayList<MainModels.NavigationViewModel> {
            return arrayListOf(
                    MainModels.NavigationViewModel(
                            type = Home,
                            title = getString(R.string.navigation_home),
                            icon = R.drawable.ic_home)
            )
        }
    }

    override fun getItem(position: Int): Fragment {
        return when(data[position].type) {
            Home -> homeFragment
        }
    }

    override fun getCount(): Int {
        return data.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].title
    }

    fun getPageIcon(position: Int): Int {
        return data[position].icon
    }

    override fun getTabView(position: Int, isSelected: Boolean): View {
        val view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null)

        view.nav_icon.setImageResource(this.getPageIcon(position))
        view.nav_label.text = this.getPageTitle(position)

        if(isSelected) {
            view.nav_label.setTextColor(ContextCompat.getColor(context, R.color.azure))

            // Do not tint the new job button
            if (position != 2) {
                view.nav_icon.setColorFilter(ContextCompat.getColor(context, R.color.azure))
            }
        } else {
            view.nav_label.setTextColor(ContextCompat.getColor(context, R.color.greyish_brown))

            // Do not tint the new job button
            if (position != 2) {
                view.nav_icon.setColorFilter(ContextCompat.getColor(context, R.color.greyish_brown))
            }
        }

        return view
    }

    override fun updateTabView(tab: TabLayout.Tab?, isSelected: Boolean) {
        if (tab == null)
            return

        // get inflated children Views the icon and the label by their id
        val nav_label = tab.customView?.findViewById(R.id.nav_label) as? TextView
        val nav_icon = tab.customView?.findViewById(R.id.nav_icon) as? ImageView

        if(isSelected) {
            nav_label?.setTextColor(ContextCompat.getColor(context, R.color.azure))

            // Do not tint the new job button
            if (tab.position != 2) {
                nav_icon?.setColorFilter(ContextCompat.getColor(context, R.color.azure))
            }
        } else {
            nav_label?.setTextColor(ContextCompat.getColor(context, R.color.greyish_brown))

            // Do not tint the new job button
            if (tab.position != 2) {
                nav_icon?.setColorFilter(ContextCompat.getColor(context, R.color.greyish_brown))
            }
        }
    }

}