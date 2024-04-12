package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.data.remote.source.AuthKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.models.AuthRequest
import com.larkes.hackathonpriceapp.data.settings.source.AuthSettingsDataSource
import com.larkes.hackathonpriceapp.data.settings.source.models.TokenEntity
import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.model.Token
import com.larkes.hackathonpriceapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authKtorDataSource: AuthKtorDataSource,
    private val authSettingsDataSource: AuthSettingsDataSource
):AuthRepository {
    override suspend fun performLogin(authData: AuthData) {
        val token = authKtorDataSource.sendLogin(AuthRequest(
            email = authData.email,
            number = authData.number,
            password = authData.password
        ))
        authSettingsDataSource.putToken(TokenEntity(token = token))
    }

    override suspend fun performRegistration(authData: AuthData) {
        authKtorDataSource.sendRegistration(
            AuthRequest(
                email = authData.email,
                number = authData.number,
                password = authData.password
            )
        )
    }

    override suspend fun fetchAuthToken(): Token? {
        val token = authSettingsDataSource.fetchToken() ?: return null
        return Token(token = token.token)
    }
}