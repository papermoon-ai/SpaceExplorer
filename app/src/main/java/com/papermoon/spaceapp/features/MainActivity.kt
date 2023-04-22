package com.papermoon.spaceapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.elevation.SurfaceColors
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp

class MainActivity : AppCompatActivity() {

    private val navigator = object: AppNavigator(this, R.id.fragment_container) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment
        ) {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val color = SurfaceColors.SURFACE_1.getColor(this)
        window.statusBarColor = color

        SpaceApp.INSTANCE.router.replaceScreen(Screens.overviewScreen())
    }

    override fun onResume() {
        super.onResume()
        SpaceApp.INSTANCE.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        SpaceApp.INSTANCE.navigationHolder.removeNavigator()
        super.onPause()
    }
}
