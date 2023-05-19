package com.papermoon.spaceapp.features

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.color.MaterialColors
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.ActivityMainBinding
import com.papermoon.spaceapp.features.home.ui.HomeFragment
import com.papermoon.spaceapp.features.settings.ui.SettingsFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

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
            // Settings transition animation disable
            else if (nextFragment is SettingsFragment || nextFragment is HomeFragment) {
                return
            } else {
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            setThemeFromPreferences()
        }

        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = MaterialColors.getColor(
            this,
            com.google.android.material.R.attr.colorPrimary,
            Color.BLACK
        )

        if (savedInstanceState == null) {
            SpaceApp.INSTANCE.router.replaceScreen(Screens.overviewScreen())
        }
    }

    private fun setThemeFromPreferences() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val defaultValue = AppCompatDelegate.MODE_NIGHT_YES
        val theme = sharedPref.getInt(getString(R.string.dark_mode_key), defaultValue)
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(getString(R.string.dark_mode_key), AppCompatDelegate.MODE_NIGHT_YES)
            apply()
        }

        recreate()
    }

    fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(getString(R.string.dark_mode_key), AppCompatDelegate.MODE_NIGHT_NO)
            apply()
        }

        recreate()
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
