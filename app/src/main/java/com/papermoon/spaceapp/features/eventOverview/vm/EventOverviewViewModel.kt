package com.papermoon.spaceapp.features.eventOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.event.SaveUpcomingEventsToLocalUseCase
import kotlinx.coroutines.launch

class EventOverviewViewModel(
    private val getUpcomingEventsFromNetworkUseCase: GetUpcomingEventsFromNetworkUseCase,
    private val getUpcomingEventsFromLocalUseCase: GetUpcomingEventsFromLocalUseCase,
    private val saveUpcomingEventsToLocalUseCase: SaveUpcomingEventsToLocalUseCase
) : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<Event>>()
    val upcomingEvents: LiveData<List<Event>>
        get() = _upcomingEvents

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadEventsMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showShimmer = MutableLiveData(false)
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    init {
        updateUpcomingEvents()
    }

    fun updateUpcomingEvents() {
        _showShimmer.value = true
        viewModelScope.launch {
            updateEventsFromNetwork()
            _upcomingEvents.value ?: updateEventsFromLocal()

            if (_upcomingEvents.value == null || _upcomingEvents.value!!.isEmpty()) {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    private suspend fun updateEventsFromNetwork() {
        val result = getUpcomingEventsFromNetworkUseCase.execute(Unit)
        result.doOnSuccess {
            _upcomingEvents.value = result.data
            saveEventsToLocal()
        }
    }

    private suspend fun updateEventsFromLocal() {
        val result = getUpcomingEventsFromLocalUseCase.execute(Unit)
        result.doOnSuccess {
            _upcomingEvents.value = result.data
        }
    }

    private suspend fun saveEventsToLocal() {
        saveUpcomingEventsToLocalUseCase.execute(_upcomingEvents.value!!)
    }

    fun doneLoadingMessage() {
        _showShimmer.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
