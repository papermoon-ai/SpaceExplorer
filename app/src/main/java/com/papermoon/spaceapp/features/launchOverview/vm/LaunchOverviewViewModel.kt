package com.papermoon.spaceapp.features.launchOverview.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.resource.doOnFailure
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.GetUpcomingLaunchesFromNetworkUserCase
import kotlinx.coroutines.launch

class LaunchOverviewViewModel(
    private val getUpcomingLaunchesFromNetworkUserCase: GetUpcomingLaunchesFromNetworkUserCase
) : ViewModel() {

    private val _upcomingLaunches = MutableLiveData<List<Launch>>()
    val upcomingLaunches: LiveData<List<Launch>>
        get() = _upcomingLaunches

    init {
        _upcomingLaunches.value = emptyList()
        updateUpcomingLaunches()
    }

    fun updateUpcomingLaunches() {
        viewModelScope.launch {
            val result = getUpcomingLaunchesFromNetworkUserCase.execute(Unit)

            result.doOnSuccess {
                _upcomingLaunches.value = result.data
            }
            result.doOnFailure {
                Log.e("Launches", it.toString())
            }
        }
    }
}
