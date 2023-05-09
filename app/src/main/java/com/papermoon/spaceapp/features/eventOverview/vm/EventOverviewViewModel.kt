package com.papermoon.spaceapp.features.eventOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.resource.doOnFailure
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromNetworkUseCase
import kotlinx.coroutines.launch

class EventOverviewViewModel(
    private val getUpcomingEventsFromNetworkUseCase: GetUpcomingEventsFromNetworkUseCase
) : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<Event>>()
    val upcomingEvents: LiveData<List<Event>>
        get() = _upcomingEvents

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadRateMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showShimmer = MutableLiveData(false)
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    init {
        updateUpcomingEvents()
    }

    fun updateUpcomingEvents() {
        viewModelScope.launch {
            _showShimmer.value = true

            val result = getUpcomingEventsFromNetworkUseCase.execute(Unit)

            result.doOnSuccess {
                _upcomingEvents.value = result.data
            }
            result.doOnFailure {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    fun doneLoadingMessage() {
        _showShimmer.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
