package com.papermoon.spaceapp.features.astronautOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.astronaut.GetAstronautsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.astronaut.GetAstronautsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.astronaut.SaveAstronautsToLocalUseCase
import kotlinx.coroutines.launch

class AstronautOverviewViewModel(
    private val getAstronautsFromNetworkUseCase: GetAstronautsFromNetworkUseCase,
    private val getAstronautsFromLocalUseCase: GetAstronautsFromLocalUseCase,
    private val saveAstronautsToLocalUseCase: SaveAstronautsToLocalUseCase,
) : ViewModel() {

    private var _astronautList = MutableLiveData<List<Astronaut>>()
    val astronautList: LiveData<List<Astronaut>>
        get() = _astronautList

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadAstronautsMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showShimmer = MutableLiveData(false)
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    init {
        updateAstronauts()
    }

    fun updateAstronauts() {
        _showShimmer.value = true
        viewModelScope.launch {
            updateAstronautsFromNetwork()
            _astronautList.value ?: updateAstronautsFromLocal()

            if (_astronautList.value == null || _astronautList.value!!.isEmpty()) {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    private suspend fun updateAstronautsFromNetwork() {
        val result = getAstronautsFromNetworkUseCase.execute(Unit)
        result.doOnSuccess {
            _astronautList.value = result.data
            saveAstronautsToLocal()
        }
    }

    private suspend fun updateAstronautsFromLocal() {
        val result = getAstronautsFromLocalUseCase.execute(Unit)
        result.doOnSuccess {
            _astronautList.value = result.data
        }
    }

    private suspend fun saveAstronautsToLocal() {
        saveAstronautsToLocalUseCase.execute(_astronautList.value!!)
    }

    fun doneLoadingMessage() {
        _showShimmer.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
