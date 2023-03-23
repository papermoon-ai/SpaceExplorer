package com.papermoon.spaceapp

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.papermoon.spaceapp.features.launch.ui.LaunchOverviewFragment
import com.papermoon.spaceapp.features.overview.ui.OverviewFragment

object Screens {
    fun overviewScreen() = FragmentScreen { OverviewFragment() }
    fun launchOverviewScreen() = FragmentScreen { LaunchOverviewFragment() }
}
