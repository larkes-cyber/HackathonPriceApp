package com.larkes.hackathonpriceapp.di

import com.larkes.hackathonpriceapp.domain.repository.TestRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object InjectRepositories: KoinComponent {

    val testRepository:TestRepository = get<TestRepository>()

}