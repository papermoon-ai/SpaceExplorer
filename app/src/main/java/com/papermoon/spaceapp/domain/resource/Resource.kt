package com.papermoon.spaceapp.domain.resource

sealed class Resource<T>(val data: T? = null, val exception: Throwable? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Failure<T>(exception: Throwable?) : Resource<T>(exception = exception)
}

inline fun <reified T> Resource<T>.doOnSuccess(callback: (data: T) -> Unit) {
    if (this is Resource.Success) {
        callback(data!!)
    }
}

inline fun <reified T> Resource<T>.doOnFailure(callback: (exception: Throwable?) -> Unit) {
    if (this is Resource.Failure) {
        callback(exception!!)
    }
}
