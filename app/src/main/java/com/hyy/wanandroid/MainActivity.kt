package com.hyy.wanandroid

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.hyy.wanandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //不用原有的navigation 的原因是 navigation 里面默认用replace 来管理fragment
    private lateinit var binding: ActivityMainBinding

    private val onNavListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item ->
        false
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

    }


}
