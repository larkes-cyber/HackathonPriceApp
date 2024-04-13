package com.larkes.hackathonpriceapp.domain.usecase

import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.utils.Resource

class UseCheckAuth(
    private val authRepository: AuthRepository
) {

    suspend fun execute():Resource<String>{
        return try {

            val token = authRepository.fetchAuthToken()

            if(token == null || token.refreshToken.isEmpty()){
                Resource.Error("Invalid token")
            }else{
                Resource.Success("success")
            }

        }catch (e:Exception){
            Resource.Error(e.message ?: "")
        }
    }

}