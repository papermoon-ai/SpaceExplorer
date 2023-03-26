package com.papermoon.spaceapp.features.astronautOverview.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.model.Astronaut
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

    init {
        updateAstronauts()
    }

    fun updateAstronauts() {
        viewModelScope.launch {
            val result = getAstronautsFromNetworkUseCase.execute(Unit)
            result.doOnSuccess {
                _astronautList.value = result.data
            }
            result.doOnFailure {
                Log.e("Astronauts", it.toString())
            }
        }
    }
}