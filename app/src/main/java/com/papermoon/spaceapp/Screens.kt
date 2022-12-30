package com.papermoon.spaceapp

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.papermoon.spaceapp.features.overview.ui.OverviewFragment

object Screens {
    fun OverviewScreen() = FragmentScreen { OverviewFragment() }
}