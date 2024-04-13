package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.data.remote.source.AuthKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.models.AuthRequest
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenRequest
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
            login = authData.email ?: authData.number ?: "",
            password = authData.password
        ))
        authSettingsDataSource.putToken(TokenEntity(  accessToken = token.access,
            refreshToken = token.refresh))
    }

    override suspend fun performRegistration(authData: AuthData) {
        authKtorDataSource.sendRegistration(
            AuthRequest(
                login = authData.email ?: authData.number ?: "",
                password = authData.password
            )
        )
    }

    override suspend fun fetchAuthToken(): Token? {
        val token = authSettingsDataSource.fetchToken() ?: return null
        return Token(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken
        )
    }

    override suspend fun checkAccessToken() {
        val token = authSettingsDataSource.fetchToken() ?: return
        try {
            authKtorDataSource.checkAccessToken(TokenRequest(
                token = token.accessToken
            ))
        }catch (e:Exception){
            authKtorDataSource.refreshToken(TokenRequest(token = token.refreshToken))
        }

    }

}