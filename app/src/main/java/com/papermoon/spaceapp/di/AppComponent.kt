package com.papermoon.spaceapp.di

import com.papermoon.spaceapp.features.overview.vm.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::OverviewViewModel)
}