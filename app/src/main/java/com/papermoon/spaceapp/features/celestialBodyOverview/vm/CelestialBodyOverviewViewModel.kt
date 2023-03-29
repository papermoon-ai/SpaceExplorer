package com.papermoon.spaceapp.features.celestialBodyOverview.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.CelestialBody
import com.papermoon.spaceapp.domain.resource.doOnFailure
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.GetPlanetsFromNetworkUseCase
import kotlinx.coroutines.launch

class CelestialBodyOverviewViewModel(
    private val getPlanetsFromNetworkUseCase: GetPlanetsFromNetworkUseCase
) : ViewModel() {

    private var _planets = MutableLiveData<List<CelestialBody>>()
    val planets: LiveData<List<CelestialBody>>
        get() = _planets

    init {
        updatePlanets()
    }

    fun updatePlanets() {
        viewModelScope.launch {
            val result = getPlanetsFromNetworkUseCase.execute(Unit)
            result.doOnSuccess {
                _planets.value = result.data
            }
            result.doOnFailure {
                Log.e("Planets", it.toString())
            }
        }
    }
}
