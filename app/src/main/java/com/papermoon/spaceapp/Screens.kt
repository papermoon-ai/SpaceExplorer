package com.papermoon.spaceapp

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.papermoon.spaceapp.domain.model.Astronaut
import com.papermoon.spaceapp.domain.model.Launch
import com.papermoon.spaceapp.features.astronaut.ui.AstronautFragment
import com.papermoon.spaceapp.features.astronautOverview.ui.AstronautOverviewFragment
import com.papermoon.spaceapp.features.launch.ui.LaunchFragment
import com.papermoon.spaceapp.features.launchOverview.ui.LaunchOverviewFragment
import com.papermoon.spaceapp.features.overview.ui.OverviewFragment

object Screens {
    fun overviewScreen() = FragmentScreen { OverviewFragment() }
    fun launchOverviewScreen() = FragmentScreen { LaunchOverviewFragment() }
    fun launchScreen(launch: Launch) = FragmentScreen { LaunchFragment(launch) }
    fun astronautOverviewScreen() = FragmentScreen { AstronautOverviewFragment() }
    fun astronautScreen(astronaut: Astronaut) = FragmentScreen { AstronautFragment(astronaut) }
}
