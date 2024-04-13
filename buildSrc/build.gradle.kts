plugins{
    `kotlin-dsl`
}

repositories{
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://plugins.gradle.org/m2/")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies{
    implementation(Dependencies.Kotlin.gradlePlugin)
    implementation(Dependencies.Android.gradlePlugin)
    implementation(Dependencies.Compose.gradlePlugin)
    implementation(Dependencies.Serialization.gradlePlugin)
    implementation(Dependencies.SqlDelight.gradlePlugin)
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.44")
}

kotlin{
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}