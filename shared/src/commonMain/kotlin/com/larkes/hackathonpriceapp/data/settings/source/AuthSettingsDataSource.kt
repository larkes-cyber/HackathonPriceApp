package com.larkes.hackathonpriceapp.data.settings.source

import com.larkes.hackathonpriceapp.data.settings.source.models.TokenEntity
import com.russhwolf.settings.Settings

class AuthSettingsDataSource(
    private val settings: Settings
) {

    suspend fun putToken(tokenEntity: TokenEntity){
        settings.putString(
            key = ACCESS_TOKEN_KEY,
            value = tokenEntity.accessToken
        )
        settings.putString(
            key = REFRESH_TOKEN_KEY,
            value = tokenEntity.refreshToken
        )
    }

    suspend fun fetchToken():TokenEntity?{
        val accessToken = settings.getString(ACCESS_TOKEN_KEY, "").ifEmpty { null }
        val refreshToken = settings.getString(REFRESH_TOKEN_KEY, "").ifEmpty { null }
        if(accessToken == null || refreshToken == null) return null
         return TokenEntity(accessToken = accessToken, refreshToken = refreshToken)
    }

    companion object{
        private const val ACCESS_TOKEN_KEY = "access_token_key"
        private const val REFRESH_TOKEN_KEY = "refresh_token_key"
    }
}