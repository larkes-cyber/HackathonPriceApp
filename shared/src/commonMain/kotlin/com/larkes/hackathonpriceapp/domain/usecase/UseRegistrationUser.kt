package com.larkes.hackathonpriceapp.domain.usecase

import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.utils.Resource

class UseRegistrationUser(
    private val authRepository: AuthRepository
) {

    suspend fun execute(authData: AuthData){
        authRepository.performRegistration(authData)
    }

}