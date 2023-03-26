package com.papermoon.spaceapp.di

import com.papermoon.spaceapp.features.astronautOverview.vm.AstronautOverviewViewModel
import com.papermoon.spaceapp.features.launchOverview.vm.LaunchOverviewViewModel
import com.papermoon.spaceapp.features.overview.vm.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::OverviewViewModel)
    viewModel { LaunchOverviewViewModel(get()) }
    viewModel { AstronautOverviewViewModel(get()) }
}
