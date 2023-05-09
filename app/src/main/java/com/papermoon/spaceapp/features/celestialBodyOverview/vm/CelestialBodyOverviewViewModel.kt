package com.papermoon.spaceapp.features.celestialBodyOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.celestialBody.GetPlanetsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.celestialBody.GetPlanetsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.celestialBody.SavePlanetsToLocalUseCase
import kotlinx.coroutines.launch

class CelestialBodyOverviewViewModel(
    private val getPlanetsFromNetworkUseCase: GetPlanetsFromNetworkUseCase,
    private val getPlanetsFromLocalUseCase: GetPlanetsFromLocalUseCase,
    private val savePlanetsToLocalUseCase: SavePlanetsToLocalUseCase
) : ViewModel() {

    private var _planets = MutableLiveData<List<CelestialBody>>()
    val planets: LiveData<List<CelestialBody>>
        get() = _planets

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadRateMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showShimmer = MutableLiveData(false)
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    init {
        updatePlanets()
    }

    fun updatePlanets() {
        _showShimmer.value = true

        viewModelScope.launch {
            updatePlanetsFromNetwork()
            _planets.value ?: updatePlanetsFromLocal()

            if (_planets.value == null || _planets.value!!.isEmpty()) {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    private suspend fun updatePlanetsFromNetwork() {
        val result = getPlanetsFromNetworkUseCase.execute(Unit)
        result.doOnSuccess {
            _planets.value = result.data
            savePlanetsToLocal()
        }
    }

    private suspend fun updatePlanetsFromLocal() {
        val result = getPlanetsFromLocalUseCase.execute(Unit)
        result.doOnSuccess {
            _planets.value = result.data
        }
    }

    private suspend fun savePlanetsToLocal() {
        savePlanetsToLocalUseCase.execute(_planets.value!!)
    }

    fun doneLoadingMessage() {
        _showShimmer.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
