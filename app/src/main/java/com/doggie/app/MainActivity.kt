package com.doggie.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.doggie.app.databinding.ActivityMainBinding

class MainActivity : DoggieActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.fragment_container)
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.app_navigation)

        binding.navigationView.setupWithNavController(navController)
    }
}