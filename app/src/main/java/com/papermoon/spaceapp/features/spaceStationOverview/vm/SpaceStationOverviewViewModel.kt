package com.papermoon.spaceapp.features.spaceStationOverview.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.resource.doOnFailure
import com.papermoon.spaceapp.domain.resource.doOnSuccess
import com.papermoon.spaceapp.domain.usecase.GetActiveSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetSpaceStationsFromNetworkUseCase
import kotlinx.coroutines.launch

class SpaceStationOverviewViewModel(
    private val getSpaceStationsFromNetworkUseCase: GetSpaceStationsFromNetworkUseCase,
    private val getActiveSpaceStationsFromNetworkUseCase: GetActiveSpaceStationsFromNetworkUseCase
) : ViewModel() {

    private var _spaceStationsList = MutableLiveData<List<SpaceStation>>()
    val spaceStationsList: LiveData<List<SpaceStation>>
        get() = _spaceStationsList

    init {
        updateSpaceStationsList()
    }

    fun updateSpaceStationsList() {
        viewModelScope.launch {
            val result = getSpaceStationsFromNetworkUseCase.execute(Unit)
            result.doOnSuccess {
                _spaceStationsList.value = result.data
            }
            result.doOnFailure {
                Log.e("SpaceStations", it.toString())
            }
        }
    }
}
