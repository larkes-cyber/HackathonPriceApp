plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("app.cash.sqldelight")
    id("org.jetbrains.kotlin.plugin.serialization")
}

sqldelight {
    databases {
        create("PriceAppDatabase") {
            packageName.set("com.larkes.hackathonpriceapp")
            generateAsync.set(true)
        }
    }
    linkSqlite = true
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(Dependencies.SqlDelight.androidDriver)
            implementation(Dependencies.Ktor.android_client)
            implementation(Dependencies.Settings.test)
            implementation(Dependencies.SqlDelight.testDriver)

        }
        commonMain.dependencies {
            implementation(Dependencies.Koin.core)
            implementation(Dependencies.Ktor.core)
            implementation(Dependencies.Ktor.cio)
            implementation(Dependencies.Ktor.negotiation)
            implementation(Dependencies.Ktor.serialization)
            implementation(Dependencies.Serialization.serialization)
            implementation(Dependencies.Settings.test)
            implementation(Dependencies.Settings.settings)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidNativeTest.dependencies {
            implementation(Dependencies.SqlDelight.testDriver)
            implementation(Dependencies.Ktor.test)
        }
        iosMain.dependencies {
            implementation(Dependencies.SqlDelight.iosDriver)
            implementation(Dependencies.Ktor.ios_client)
            implementation(Dependencies.Ktor.ios_darwing)
        }
    }
}

android {
    namespace = "com.larkes.hackathonpriceapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}