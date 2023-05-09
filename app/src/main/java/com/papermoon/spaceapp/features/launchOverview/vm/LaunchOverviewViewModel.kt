package com.papermoon.spaceapp.features.launchOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.resource.doOnFailure
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromNetworkUseCase
import kotlinx.coroutines.launch

class LaunchOverviewViewModel(
    private val getUpcomingLaunchesFromNetworkUseCase: GetUpcomingLaunchesFromNetworkUseCase
) : ViewModel() {

    private val _upcomingLaunches = MutableLiveData<List<Launch>>()
    val upcomingLaunches: LiveData<List<Launch>>
        get() = _upcomingLaunches

    private val _showUnableToUpdateMessage = MutableLiveData(false)
    val showUnableToLoadRateMessage: LiveData<Boolean>
        get() = _showUnableToUpdateMessage

    private val _showShimmer = MutableLiveData(false)
    val showShimmer: LiveData<Boolean>
        get() = _showShimmer

    init {
        updateUpcomingLaunches()
    }

    fun updateUpcomingLaunches() {
        viewModelScope.launch {
            _showShimmer.value = true

            val result = getUpcomingLaunchesFromNetworkUseCase.execute(Unit)

            result.doOnSuccess {
                _upcomingLaunches.value = result.data
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
