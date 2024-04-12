package com.larkes.hackathonpriceapp.domain.core

import com.larkes.hackathonpriceapp.domain.usecase.UseFetchStores
import com.larkes.hackathonpriceapp.domain.usecase.UseLoginUser
import com.larkes.hackathonpriceapp.domain.usecase.UsePerformPrice
import com.larkes.hackathonpriceapp.domain.usecase.UseRegistrationUser
import com.larkes.hackathonpriceapp.domain.usecase.UseSendPricePhoto
import org.koin.dsl.module

val domainModule = module {
    single {
        UseLoginUser(get())
    }
    single {
        UseRegistrationUser(get())
    }
    single {
        UseSendPricePhoto(get())
    }
    single {
        UsePerformPrice(get())
    }
    single {
        UseFetchStores(get())
    }

}