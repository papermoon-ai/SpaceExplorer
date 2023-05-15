package com.papermoon.spaceapp.features.spaceStationOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.spacestation.GetSpaceStationsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.GetSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.SaveSpaceStationsToLocalUseCase
import kotlinx.coroutines.launch

class SpaceStationOverviewViewModel(
    private val getSpaceStationsFromNetworkUseCase: GetSpaceStationsFromNetworkUseCase,
    private val getSpaceStationsFromLocalUseCase: GetSpaceStationsFromLocalUseCase,
    private val saveSpaceStationsToLocalUseCase: SaveSpaceStationsToLocalUseCase
) : ViewModel() {

    private var _spaceStationsList = MutableLiveData<List<SpaceStation>>()
    val spaceStationsList: LiveData<List<SpaceStation>>
        get() = _spaceStationsList

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadStationsMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showShimmer = MutableLiveData(false)
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    init {
        updateSpaceStationsList()
    }

    fun updateSpaceStationsList() {
        _showShimmer.value = true

        viewModelScope.launch {
            updateSpaceStationsFromNetwork()
            _spaceStationsList.value ?: updateSpaceStationsFromLocal()

            if (_spaceStationsList.value == null || _spaceStationsList.value!!.isEmpty()) {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    private suspend fun updateSpaceStationsFromNetwork() {
        val result = getSpaceStationsFromNetworkUseCase.execute(Unit)
        result.doOnSuccess {
            _spaceStationsList.value = result.data
            saveSpaceStationsToNetwork()
        }
    }

    private suspend fun updateSpaceStationsFromLocal() {
        val result = getSpaceStationsFromLocalUseCase.execute(Unit)
        result.doOnSuccess {
            _spaceStationsList.value = result.data
        }
    }

    private suspend fun saveSpaceStationsToNetwork() {
        saveSpaceStationsToLocalUseCase.execute(_spaceStationsList.value!!)
    }

    fun doneLoadingMessage() {
        _showShimmer.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
