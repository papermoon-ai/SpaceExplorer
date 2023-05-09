package com.papermoon.spaceapp.domain.model.celestialbody

import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalPeriod

data class Period(
    val years: Int,
    val days: Int,
    val hours: Int,
    val minutes: Int
)

fun Period.toLocalObject(): LocalPeriod {
    return LocalPeriod(years, days, hours, minutes)
}
