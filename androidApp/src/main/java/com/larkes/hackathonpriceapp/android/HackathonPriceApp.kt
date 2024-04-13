package com.larkes.hackathonpriceapp.android

import android.app.Application
import com.larkes.hackathonpriceapp.di.PlatformSDK
import com.larkes.hackathonpriceapp.domain.platform.PlatformConfiguration
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HackathonPriceApp:Application(){

    override fun onCreate() {
        super.onCreate()

        PlatformSDK.init(PlatformConfiguration(this))

    }

}