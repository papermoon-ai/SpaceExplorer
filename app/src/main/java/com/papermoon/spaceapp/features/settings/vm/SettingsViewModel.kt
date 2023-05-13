package com.papermoon.spaceapp.features.settings.vm

import android.content.Context
import android.net.TrafficStats
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papermoon.spaceapp.domain.usecase.astronaut.DeleteAstronautsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.celestialBody.DeleteCelestialBodiesFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.event.DeleteEventsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.launch.DeleteLaunchesFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.DeleteSpaceStationsFromLocalUseCase
import kotlinx.coroutines.launch
import java.io.File

class SettingsViewModel(
    private val deleteAstronautsFromLocalUseCase: DeleteAstronautsFromLocalUseCase,
    private val deleteCelestialBodiesFromLocalUseCase: DeleteCelestialBodiesFromLocalUseCase,
    private val deleteEventsFromLocalUseCase: DeleteEventsFromLocalUseCase,
    private val deleteLaunchesFromLocalUseCase: DeleteLaunchesFromLocalUseCase,
    private val deleteSpaceStationsFromLocalUseCase: DeleteSpaceStationsFromLocalUseCase
) : ViewModel() {

    private var _storageUsage = MutableLiveData<Long>()
    val storageUsage: LiveData<Long>
        get() = _storageUsage

    private var _trafficUsage = MutableLiveData<Long>()
    val trafficUsage: LiveData<Long>
        get() = _trafficUsage

    fun updateStorageUsage(context: Context) {
        _storageUsage.value = getDirSize(context.cacheDir) + getDirSize(context.externalCacheDir!!)
    }

    fun updateTrafficUsage(uid: Int) {
        _trafficUsage.value = TrafficStats.getUidRxBytes(uid) + TrafficStats.getUidTxBytes(uid)
    }

    fun clearStorage(context: Context) {
        viewModelScope.launch {
            clearCache(context)
            clearDatabase()
        }
    }

    private fun getDirSize(dir: File): Long {
        var size: Long = 0
        for (file in dir.listFiles()!!) {
            if (file != null && file.isDirectory) {
                size += getDirSize(file)
            } else if (file != null && file.isFile) {
                size += file.length()
            }
        }
        return size
    }

    private fun clearCache(context: Context) {
        context.cacheDir.deleteRecursively()
        context.externalCacheDir?.deleteRecursively()
    }

    private suspend fun clearDatabase() {
        deleteAstronautsFromLocalUseCase.execute(Unit)
        deleteCelestialBodiesFromLocalUseCase.execute(Unit)
        deleteEventsFromLocalUseCase.execute(Unit)
        deleteLaunchesFromLocalUseCase.execute(Unit)
        deleteSpaceStationsFromLocalUseCase.execute(Unit)
    }
}
