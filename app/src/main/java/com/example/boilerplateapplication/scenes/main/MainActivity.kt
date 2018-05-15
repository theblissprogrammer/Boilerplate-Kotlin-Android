package com.example.boilerplateapplication.scenes.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.example.boilerplateapplication.scenes.main.controls.MainPagerAdapter
import android.support.v7.widget.Toolbar
import com.example.boilerplateapplication.R
import com.example.boilerplateapplication.common.activities.BaseActivity
import com.example.boilerplateapplication.common.protocols.NavigationInterface
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: BaseActivity(), HasDependencies {

    private lateinit var mMainPagerAdapter: MainPagerAdapter

    private val preferencesWorker: PreferencesWorkerType by lazy {
        dependencies.resolvePreferencesWorker()
    }

    private var routeListener: ((Fragment) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.AppTheme_NoActionBar)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        mMainPagerAdapter = MainPagerAdapter(this.applicationContext, supportFragmentManager)
        /*pager.adapter = mMainPagerAdapter
        pager.offscreenPageLimit = 4

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                invalidateOptionsMenu(position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        invalidateOptionsMenu(0)*/

        // Initialize fragments
        if (savedInstanceState == null) {
            /*val ft = supportFragmentManager
                    .beginTransaction()

            for (i in 0 until mMainPagerAdapter.count) {
                val fragment = mMainPagerAdapter.getItem(i)
                ft.add(R.id.main_fragment, fragment, fragment::class.java.simpleName)
                ft.hide(fragment)
            }

            ft.commit()*/
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            var tabUnselected: TabLayout.Tab? = null

            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.apply {
                    mMainPagerAdapter.updateTabView(tab, isSelected = false)
                    tabUnselected = tab
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.apply {
                    // Make sure everything is up to date
                    supportFragmentManager.executePendingTransactions()

                    mMainPagerAdapter.updateTabView(tab, isSelected = true)

                    val ft = supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)

                    for (i in 0 until mMainPagerAdapter.count) {
                        val fragment = mMainPagerAdapter.getItem(i)
                        if (fragment.isAdded && i != tab.position) {
                            ft.hide(fragment)
                        }

                        if (fragment.isAdded && i == tab.position) {
                            ft.show(fragment)
                        } else if (i == tab.position) {
                            ft.add(R.id.main_fragment, fragment, fragment::class.java.simpleName)
                        }
                    }

                    ft.commit()

                    if (routeListener != null) {
                        // Make sure everything is up to date
                        supportFragmentManager.executePendingTransactions()
                        routeListener?.invoke(mMainPagerAdapter.getItem(tab.position))
                        routeListener = null
                    }
                }
            }

        })

        for (i in 0 until mMainPagerAdapter.count) {
            tabs.addTab(tabs.newTab(), i, i == 0)
        }

        for (i in 0 until tabs.tabCount) {
            tabs.getTabAt(i)?.customView = mMainPagerAdapter.getTabView(i, isSelected = i == 0)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun invalidateOptionsMenu(position: Int) {
        setSupportActionBar(null)

        (0 until mMainPagerAdapter.count)
                .map { mMainPagerAdapter.getItem(it) }
                .forEachIndexed { index, fragment ->
                    if (index == position) {
                        val toolbar = fragment.view?.findViewById<Toolbar>(R.id.toolbar)
                        setSupportActionBar(toolbar)
                    }

                    fragment.setHasOptionsMenu(index == position)
                }

        invalidateOptionsMenu()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        when (id) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments.firstOrNull { it.isAdded && it.isVisible }

        when (fragment) {
            is NavigationInterface -> {
                fragment.onBackPressed()
            }
            else -> { super.onBackPressed() }
        }
    }

    override fun onResume() {
        super.onResume()

        if (intent.action == Intent.ACTION_VIEW && intent.data != null) {
            val data = intent.data ?: return

            // TODO: Add logic
            when {
                data.path.startsWith(prefix = "") -> {
                    try {
                    } catch(e: Exception) { /* TODO: Load url through browser */ }
                }
            }

            intent.data = null
        }
    }
}
