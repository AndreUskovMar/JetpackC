package ru.auskov.jetpackc

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wiFiManager: WiFiManager
) : ViewModel() {
    init {
        Log.d("MyLog", "Instance Module 1 $wiFiManager")
    }

    fun connect() {
        wiFiManager.connect()
    }
}