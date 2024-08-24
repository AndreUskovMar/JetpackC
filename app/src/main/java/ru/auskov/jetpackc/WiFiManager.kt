package ru.auskov.jetpackc

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
//import javax.inject.Inject
//import javax.inject.Singleton

@ActivityScoped
class WiFiManager(private val settings: WiFiSettings) {
    fun connect() {
        settings.openConnection()
    }

    fun writeMessage() {
        settings.writeBytes()
    }
}

class WiFiSettings() {
    fun openConnection() {
        Log.d("MyLog", "Connected")
    }

    fun writeBytes() {
        Log.d("MyLog", "Hello")
    }
}