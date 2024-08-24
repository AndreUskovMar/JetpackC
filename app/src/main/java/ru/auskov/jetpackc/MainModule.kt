package ru.auskov.jetpackc

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {
    @Provides
    @ActivityRetainedScoped
    fun getWiFiManager(settings: WiFiSettings): WiFiManager {
        return WiFiManager(settings)
    }

    @Provides
    fun getWiFiSettings(): WiFiSettings {
        return WiFiSettings()
    }
}