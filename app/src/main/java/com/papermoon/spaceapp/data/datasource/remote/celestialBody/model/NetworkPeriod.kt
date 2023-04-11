package com.papermoon.spaceapp.data.datasource.remote.celestialBody.model

import com.papermoon.spaceapp.domain.model.celestialbody.Period

data class NetworkPeriod(
    val years: Int,
    val days: Int,
    val hours: Int,
    val minutes: Int
)

fun NetworkPeriod.toDomainObject(): Period {
    return Period(years, days, hours, minutes)
}
