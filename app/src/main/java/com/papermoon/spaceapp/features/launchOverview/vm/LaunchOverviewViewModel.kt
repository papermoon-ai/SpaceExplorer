package com.papermoon.spaceapp.features.launchOverview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.launch.SaveUpcomingLaunchesToLocalUseCase
import kotlinx.coroutines.launch

class LaunchOverviewViewModel(
    private val getUpcomingLaunchesFromNetworkUseCase: GetUpcomingLaunchesFromNetworkUseCase,
    private val getUpcomingLaunchesFromLocalUseCase: GetUpcomingLaunchesFromLocalUseCase,
    private val saveUpcomingLaunchesToLocalUseCase: SaveUpcomingLaunchesToLocalUseCase
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
        _showShimmer.value = true
        viewModelScope.launch {
            updateLaunchesFromNetwork()
            _upcomingLaunches.value ?: updateLaunchesFromLocal()

            if (_upcomingLaunches.value == null || _upcomingLaunches.value!!.isEmpty()) {
                _showUnableToUpdateMessage.value = true
            }
        }
    }

    private suspend fun updateLaunchesFromNetwork() {
        val result = getUpcomingLaunchesFromNetworkUseCase.execute(Unit)
        result.doOnSuccess {
            _upcomingLaunches.value = result.data
            saveLaunchesToLocal()
        }
    }

    private suspend fun updateLaunchesFromLocal() {
        val result = getUpcomingLaunchesFromLocalUseCase.execute(Unit)
        result.doOnSuccess {
            _upcomingLaunches.value = result.data
        }
    }

    private suspend fun saveLaunchesToLocal() {
        saveUpcomingLaunchesToLocalUseCase.execute(_upcomingLaunches.value!!)
    }

    fun doneLoadingMessage() {
        _showShimmer.value = false
    }

    fun doneUnableToLoadMessage() {
        _showUnableToUpdateMessage.value = false
    }
}
