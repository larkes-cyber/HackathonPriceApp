package com.larkes.hackathonpriceapp.di

import com.larkes.hackathonpriceapp.domain.repository.TestRepository
import com.larkes.hackathonpriceapp.domain.usecase.UseCheckAuth
import com.larkes.hackathonpriceapp.domain.usecase.UseFetchStores
import com.larkes.hackathonpriceapp.domain.usecase.UseLoginUser
import com.larkes.hackathonpriceapp.domain.usecase.UsePerformPrice
import com.larkes.hackathonpriceapp.domain.usecase.UseRegistrationUser
import com.larkes.hackathonpriceapp.domain.usecase.UseSendPricePhoto
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object InjectUseCase: KoinComponent {

    val useFetchStores:UseFetchStores = get<UseFetchStores>()
    val useLoginUser:UseLoginUser = get<UseLoginUser>()
    val usePerformPrice:UsePerformPrice  = get<UsePerformPrice>()
    val useRegistrationUser:UseRegistrationUser = get<UseRegistrationUser>()
    val useSendPricePhoto:UseSendPricePhoto = get<UseSendPricePhoto>()
    val useCheckAuth:UseCheckAuth = get<UseCheckAuth>()

}