package com.papermoon.spaceapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.elevation.SurfaceColors
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp

class MainActivity : AppCompatActivity() {
    private val navigator = AppNavigator(this, R.id.fragment_container)

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
