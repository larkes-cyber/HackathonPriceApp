package com.larkes.hackathonpriceapp.data.settings.source

import com.larkes.hackathonpriceapp.data.settings.source.models.TokenEntity
import com.russhwolf.settings.Settings

class AuthSettingsDataSource(
    private val settings: Settings
) {

    suspend fun putToken(tokenEntity: TokenEntity){
        settings.putString(
            key = TOKEN_KEY,
            value = tokenEntity.token
        )
    }

    suspend fun fetchToken():TokenEntity?{
        val token = settings.getString(TOKEN_KEY, "").ifEmpty { null }
         return if(token == null) null else TokenEntity(token)
    }

    companion object{
        private const val TOKEN_KEY = "token_key"
    }
}