package com.hyy.wanandroid

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.hyy.wanandroid.databinding.ActivityMainBinding
import com.hyy.wanandroid.ui.dashboard.DashboardFragment
import com.hyy.wanandroid.ui.home.HomeFragment
import com.hyy.wanandroid.ui.notifications.NotificationsFragment

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

    private val fragmentMap: Map<Int, Fragment> by lazy {
        hashMapOf(
            R.id.navigation_home to homeFragment,
            R.id.navigation_dashboard to dashboardFragment,
            R.id.navigation_notifications to notificationsFragment
        )
    }

    private var currSelectId = -1

    //不用原有的navigation 的原因是 navigation 里面默认用replace 来管理fragment
    private lateinit var binding: ActivityMainBinding

    private val onNavListener = BottomNavigationView.OnNavigationItemSelectedListener {
        changeFragmentById(it.itemId)
        true
    }

    private fun changeFragmentById(itemId: Int) {
        if (itemId == currSelectId) return
        val lastFragment = fragmentMap[currSelectId]
        val fragment = fragmentMap[itemId]
        currSelectId = itemId
        fragment?.apply {
            val transaction = supportFragmentManager.beginTransaction()
            if (!isAdded) transaction.add(R.id.fragment_container, fragment, javaClass.simpleName)
            transaction.show(fragment)
            lastFragment?.let {
                transaction.hide(it)
            }
            transaction.commit()
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

        binding.navView.selectedItemId = R.id.navigation_home
    }

}
