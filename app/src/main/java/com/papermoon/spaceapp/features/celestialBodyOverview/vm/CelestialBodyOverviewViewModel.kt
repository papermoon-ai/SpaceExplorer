package com.papermoon.spaceapp.features.celestialBodyOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
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

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadRateMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showLoadingMessage = MutableLiveData(false)
    val showLoadingMessage: LiveData<Boolean>
        get() = _showLoadingMessage

    init {
        updatePlanets()
    }

    fun updatePlanets() {
        viewModelScope.launch {
            _showLoadingMessage.value = true

            val result = getPlanetsFromNetworkUseCase.execute(Unit)
            result.doOnSuccess {
                _planets.value = result.data
            }
            result.doOnFailure {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    fun doneLoadingMessage() {
        _showLoadingMessage.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
