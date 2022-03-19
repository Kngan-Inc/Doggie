package com.doggie.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.doggie.app.databinding.ActivityMainBinding
import com.doggie.app.util.KeepStateNavigator
import java.lang.Exception

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

        bottomNavigationViewListener(navController = navController)

    }

    private fun bottomNavigationViewListener(navController: NavController) {
        binding.navigationView.setupWithNavController(navController)
        binding.navigationView.setOnItemReselectedListener {  item ->
            try {
                when(item.itemId) {
                    R.id.searchFragment -> {
                        Navigation.findNavController(this, R.id.search_fragment_container).navigateUp()
                    }
                }
            } catch (e: Exception) {}
            NavigationUI.onNavDestinationSelected(item, navController)
        }
    }
}