package com.larkes.hackathonpriceapp.domain.repository

import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.model.Token

interface AuthRepository {
    suspend fun performLogin(authData: AuthData)
    suspend fun performRegistration(authData: AuthData)
    suspend fun fetchAuthToken(): Token?
    suspend fun checkAccessToken()
}