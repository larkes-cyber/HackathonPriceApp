package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.domain.repository.PriceRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<PriceRepository> { PriceRepositoryImpl(get(), get()) }
}