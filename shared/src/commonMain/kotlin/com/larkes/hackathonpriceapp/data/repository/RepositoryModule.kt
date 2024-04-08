package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.domain.repository.TestRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    single<TestRepository> {
        TestRepositoryImpl()
    }

}