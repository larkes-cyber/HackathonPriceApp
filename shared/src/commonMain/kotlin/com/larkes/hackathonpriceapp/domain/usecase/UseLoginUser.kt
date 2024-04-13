package com.larkes.hackathonpriceapp.domain.usecase

import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.utils.Resource

class UseLoginUser(
    private val authRepository: AuthRepository
) {

    suspend fun execute(authData:AuthData):Resource<String>{
        return try {
            authRepository.performLogin(authData)
            Resource.Success("")
        }catch (e:Exception){
            Resource.Error(e.message ?: "")
        }
    }

}