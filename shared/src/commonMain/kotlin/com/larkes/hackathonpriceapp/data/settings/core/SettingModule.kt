package com.larkes.hackathonpriceapp.data.settings.core

import com.larkes.hackathonpriceapp.data.settings.source.AuthSettingsDataSource
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val settingModule = module {
    single { Settings() }
    single { AuthSettingsDataSource(get()) }
}