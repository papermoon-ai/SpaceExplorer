package com.papermoon.spaceapp

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.features.astronaut.ui.AstronautFragment
import com.papermoon.spaceapp.features.astronautOverview.ui.AstronautOverviewFragment
import com.papermoon.spaceapp.features.celestialBody.ui.CelestialBodyFragment
import com.papermoon.spaceapp.features.celestialBodyOverview.ui.CelestialBodyOverviewFragment
import com.papermoon.spaceapp.features.imageViewer.ui.ImageViewerFragment
import com.papermoon.spaceapp.features.launch.ui.LaunchFragment
import com.papermoon.spaceapp.features.launchOverview.ui.LaunchOverviewFragment
import com.papermoon.spaceapp.features.overview.ui.OverviewFragment
import com.papermoon.spaceapp.features.spaceStation.ui.SpaceStationFragment
import com.papermoon.spaceapp.features.spaceStationOverview.ui.SpaceStationOverviewFragment

object Screens {
    fun overviewScreen() = FragmentScreen { OverviewFragment() }
    fun launchOverviewScreen() = FragmentScreen { LaunchOverviewFragment() }
    fun launchScreen(launch: Launch) = FragmentScreen { LaunchFragment(launch) }
    fun astronautOverviewScreen() = FragmentScreen { AstronautOverviewFragment() }
    fun astronautScreen(astronaut: Astronaut) = FragmentScreen { AstronautFragment(astronaut) }
    fun spaceStationOverviewScreen() = FragmentScreen { SpaceStationOverviewFragment() }
    fun spaceStationScreen(spaceStation: SpaceStation) =
        FragmentScreen { SpaceStationFragment(spaceStation) }

    fun celestialBodyOverviewScreen() = FragmentScreen { CelestialBodyOverviewFragment() }
    fun celestialBodyScreen(celestialBody: CelestialBody) =
        FragmentScreen { CelestialBodyFragment(celestialBody) }
    fun imageViewerScreen(images: List<ImageWithDescription>, position: Int) = FragmentScreen { ImageViewerFragment(images, position) }
}
