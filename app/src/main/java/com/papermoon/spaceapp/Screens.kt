package com.papermoon.spaceapp

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.features.astronautDetail.ui.AstronautFragment
import com.papermoon.spaceapp.features.astronautOverview.ui.AstronautOverviewFragment
import com.papermoon.spaceapp.features.celestialBodyDetail.ui.CelestialBodyFragment
import com.papermoon.spaceapp.features.celestialBodyOverview.ui.CelestialBodyOverviewFragment
import com.papermoon.spaceapp.features.eventDetail.ui.EventFragment
import com.papermoon.spaceapp.features.eventOverview.ui.EventOverviewFragment
import com.papermoon.spaceapp.features.languageSelector.ui.LanguageSelectorFragment
import com.papermoon.spaceapp.features.launchDetail.ui.LaunchFragment
import com.papermoon.spaceapp.features.launchOverview.ui.LaunchOverviewFragment
import com.papermoon.spaceapp.features.home.ui.HomeFragment
import com.papermoon.spaceapp.features.settings.ui.SettingsFragment
import com.papermoon.spaceapp.features.spaceStationDetail.ui.SpaceStationFragment
import com.papermoon.spaceapp.features.spaceStationOverview.ui.SpaceStationOverviewFragment

object Screens {
    fun overviewScreen() = FragmentScreen { HomeFragment() }
    fun launchOverviewScreen() = FragmentScreen { LaunchOverviewFragment() }
    fun launchScreen(launch: Launch) = FragmentScreen { LaunchFragment.getNewInstance(launch) }
    fun astronautOverviewScreen() = FragmentScreen { AstronautOverviewFragment() }
    fun astronautScreen(astronaut: Astronaut) = FragmentScreen { AstronautFragment.getNewInstance(astronaut) }
    fun spaceStationOverviewScreen() = FragmentScreen { SpaceStationOverviewFragment() }
    fun spaceStationScreen(spaceStation: SpaceStation) =
        FragmentScreen { SpaceStationFragment.getNewInstance(spaceStation) }
    fun celestialBodyOverviewScreen() = FragmentScreen { CelestialBodyOverviewFragment() }
    fun celestialBodyScreen(celestialBody: CelestialBody) =
        FragmentScreen { CelestialBodyFragment.getNewInstance(celestialBody) }
    fun eventOverviewScreen() = FragmentScreen { EventOverviewFragment() }
    fun eventScreen(event: Event) =
        FragmentScreen { EventFragment.getNewInstance(event) }
    fun settingsScreen() = FragmentScreen { SettingsFragment() }
    fun languageSelectorScreen() = FragmentScreen { LanguageSelectorFragment() }
}
