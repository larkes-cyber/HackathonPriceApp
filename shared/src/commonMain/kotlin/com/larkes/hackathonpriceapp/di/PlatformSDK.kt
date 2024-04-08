package com.larkes.hackathonpriceapp.di

import com.larkes.hackathonpriceapp.data.database.databaseModule
import com.larkes.hackathonpriceapp.data.remote.remoteModule
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration
import com.larkes.hackathonpriceapp.data.repository.repositoryModule
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
            modules(repositoryModule)
        }.koin
        Inject.createDependencies(diTree)
    }

}