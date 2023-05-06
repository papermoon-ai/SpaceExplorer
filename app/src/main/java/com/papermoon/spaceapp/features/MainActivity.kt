package com.papermoon.spaceapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.ActivityMainBinding
import com.papermoon.spaceapp.features.overview.ui.OverviewFragment
import com.papermoon.spaceapp.features.settings.ui.SettingsFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val router = SpaceApp.INSTANCE.router
    private val navigationHolder = SpaceApp.INSTANCE.navigationHolder

    private val navigator = object : AppNavigator(this, R.id.content_main) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment
        ) {
            if (currentFragment == null) {
                return
            }
            // Bottom navigation transition animation disable
            else if (nextFragment is SettingsFragment || nextFragment is OverviewFragment) {
                return
            }
            else {
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setBottomNavigation()

        window.statusBarColor = getColor(R.color.light_gray)

        if (savedInstanceState == null) {
            SpaceApp.INSTANCE.router.replaceScreen(Screens.overviewScreen())
        }
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val currentFragment = supportFragmentManager.fragments.last()

            when (item.itemId) {
                R.id.dataOption -> {
                    if (currentFragment !is OverviewFragment) {
                        router.newRootScreen(Screens.overviewScreen())
                    }
                }
                R.id.settingsOption -> {
                    if (currentFragment !is SettingsFragment) {
                        router.newRootScreen(Screens.settingsScreen())
                    }
                }
                else -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
