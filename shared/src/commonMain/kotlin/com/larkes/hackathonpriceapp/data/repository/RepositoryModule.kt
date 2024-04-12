package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.domain.repository.PriceRepository
import com.larkes.hackathonpriceapp.domain.repository.StoreRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<PriceRepository> { PriceRepositoryImpl(get(), get()) }
    single<StoreRepository> { StoreRepositoryImpl(get(), get()) }
}