package com.papermoon.spaceapp.features.astronautOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.resource.doOnFailure
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.GetAstronautsFromNetworkUseCase
import kotlinx.coroutines.launch

class AstronautOverviewViewModel(
    private val getAstronautsFromNetworkUseCase: GetAstronautsFromNetworkUseCase
) : ViewModel() {

    private var _astronautList = MutableLiveData<List<Astronaut>>()
    val astronautList: LiveData<List<Astronaut>>
        get() = _astronautList

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadRateMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showLoadingMessage = MutableLiveData(false)
    val showLoadingMessage: LiveData<Boolean>
        get() = _showLoadingMessage

    init {
        updateAstronauts()
    }

    fun updateAstronauts() {
        viewModelScope.launch {
            _showLoadingMessage.value = true

            val result = getAstronautsFromNetworkUseCase.execute(Unit)
            result.doOnSuccess {
                _astronautList.value = result.data
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