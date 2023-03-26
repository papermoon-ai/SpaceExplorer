package com.papermoon.spaceapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.papermoon.spaceapp.R
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.elevation.SurfaceColors
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp

class MainActivity : AppCompatActivity() {
    private val navigator = AppNavigator(this, R.id.activity_main_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ativity_layout)

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
