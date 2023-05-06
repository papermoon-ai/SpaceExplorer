package com.papermoon.spaceapp.features.commons.locale

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

fun changeLocale(locale: String) {
    val appLocale = LocaleListCompat.forLanguageTags(locale)
    AppCompatDelegate.setApplicationLocales(appLocale)
}
