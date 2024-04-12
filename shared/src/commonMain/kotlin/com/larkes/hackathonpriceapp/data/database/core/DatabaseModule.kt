package com.larkes.hackathonpriceapp.data.database.core

import com.larkes.hackathonpriceapp.PriceAppDatabase
import com.larkes.hackathonpriceapp.data.database.core.SqlDelightDriverFactory
import org.koin.dsl.module

internal val databaseModule = module {
    single { PriceAppDatabase(SqlDelightDriverFactory(get()).makeDriver(PriceAppDatabase.Schema, "user")) }

}