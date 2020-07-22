package com.hyy.jetpack

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hyy.jetpack.ui.dashboard.DashboardFragment
import com.hyy.jetpack.ui.home.HomeFragment
import com.hyy.jetpack.ui.mine.MineFragment
import com.hyy.jetpack.ui.notifications.NotificationsFragment
import com.hyy.wanandroid.R
import com.hyy.wanandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val homeFragment: HomeFragment by lazy {
        HomeFragment.create()
    }

    private val dashboardFragment: DashboardFragment by lazy {
        DashboardFragment.create()
    }

    private val notificationsFragment: NotificationsFragment by lazy {
        NotificationsFragment.create()
    }

    private val mineFragment: MineFragment by lazy {
        MineFragment.create()
    }

    private val fragmentMap: Map<Int, Fragment> by lazy {
        hashMapOf(
            R.id.navigation_home to homeFragment,
            R.id.navigation_dashboard to dashboardFragment,
            R.id.navigation_notifications to notificationsFragment,
            R.id.nav_account to mineFragment
        )
    }

    //不用原有的navigation 的原因是 navigation 里面默认用replace 来管理fragment
    private lateinit var binding: ActivityMainBinding

    private val onNavListener = BottomNavigationView.OnNavigationItemSelectedListener {
        changeFragmentById(it.itemId)
        true
    }

    private fun changeFragmentById(itemId: Int) {
        val currFragment = supportFragmentManager.fragments.find { it.isVisible && it in fragmentMap.values }
        val fragment = fragmentMap.entries.find { it.key == itemId }?.value
        supportFragmentManager.beginTransaction().apply {
            currFragment?.let {
                if (it.isVisible) hide(it)
            }
            fragment?.let {
                if (it.isAdded) show(it) else add(R.id.fragment_container, it)
            }
            commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.menu.forEach {
            val view = binding.navView.findViewById<View>(it.itemId)
            view.setOnLongClickListener {
                // your logic here
                true
            }
        }
        binding.navView.setOnNavigationItemSelectedListener(onNavListener)

        if (savedInstanceState == null) {
            binding.navView.selectedItemId = R.id.navigation_home
        }
    }


    override fun onResume() {
        super.onResume()
//        setupStatusBar()
    }

    private fun setupStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val visibility = window.decorView.systemUiVisibility
            window.decorView.systemUiVisibility =
                (visibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorStatusBar)
        }
    }
}
