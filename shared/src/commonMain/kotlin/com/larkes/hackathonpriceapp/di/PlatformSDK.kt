package com.larkes.hackathonpriceapp.di

import com.larkes.hackathonpriceapp.data.database.core.databaseModule
import com.larkes.hackathonpriceapp.data.remote.core.remoteModule
import com.larkes.hackathonpriceapp.data.repository.repositoryModule
import com.larkes.hackathonpriceapp.data.settings.core.settingModule
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PlatformSDK {

    fun init(configuration: PlatformConfiguration) {
        val diTree = startKoin {
            modules(module {
                single { configuration }
            })
            modules(databaseModule)
            modules(remoteModule)
            modules(settingModule)
            modules(repositoryModule)
        }.koin
        Inject.createDependencies(diTree)
    }

}