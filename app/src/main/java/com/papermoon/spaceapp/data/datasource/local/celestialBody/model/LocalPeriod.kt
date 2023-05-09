package com.papermoon.spaceapp.data.datasource.local.celestialBody.model

import com.papermoon.spaceapp.domain.model.celestialbody.Period

data class LocalPeriod(
    val years: Int,
    val days: Int,
    val hours: Int,
    val minutes: Int
)

fun LocalPeriod.toDomainObject(): Period {
    return Period(years, days, hours, minutes)
}
