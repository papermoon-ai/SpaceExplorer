package com.papermoon.spaceapp.di

import com.papermoon.spaceapp.features.astronautOverview.vm.AstronautOverviewViewModel
import com.papermoon.spaceapp.features.celestialBodyOverview.vm.CelestialBodyOverviewViewModel
import com.papermoon.spaceapp.features.eventOverview.vm.EventOverviewViewModel
import com.papermoon.spaceapp.features.launchOverview.vm.LaunchOverviewViewModel
import com.papermoon.spaceapp.features.spaceStationOverview.vm.SpaceStationOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LaunchOverviewViewModel(get(), get(), get()) }
    viewModel { AstronautOverviewViewModel(get(), get(), get()) }
    viewModel { SpaceStationOverviewViewModel(get(), get(), get()) }
    viewModel { CelestialBodyOverviewViewModel(get(), get(), get()) }
    viewModel { EventOverviewViewModel(get()) }
}
