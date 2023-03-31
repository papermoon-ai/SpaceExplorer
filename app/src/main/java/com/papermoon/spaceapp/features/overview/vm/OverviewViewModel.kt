package com.papermoon.spaceapp.features.overview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.domain.model.MenuOption

class OverviewViewModel : ViewModel() {

    private var _options = MutableLiveData<List<MenuOption>>()
    val options: LiveData<List<MenuOption>>
        get() = _options

    init {
        _options.value = listOf(
            MenuOption(
                "Planets",
                "Explore real-time planet data",
                R.drawable.ic_planet
            ),
            MenuOption(
                "Space stations",
                "Observe real-time data of Internation Space Station",
                R.drawable.ic_space_station
            ),
            MenuOption(
                "Astronauts",
                "Explore who is in space right now",
                R.drawable.ic_astronaut
            ),
            MenuOption(
                "Launches",
                "Look what rockets are about to launch",
                R.drawable.ic_rocket
            )
        )
    }
}
