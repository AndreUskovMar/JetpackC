package ru.auskov.jetpackc

import android.content.Context
import androidx.datastore.dataStore

private val Context.protoDataStore by dataStore("settings.json", SettingSerializer)
class ProtoDataStoreManager(private val context: Context) {
    suspend fun saveColor(color: ULong) {
        context.protoDataStore.updateData {data ->
            data.copy(bgColor = color)
        }
    }

    suspend fun saveFontSize(size: Int) {
        context.protoDataStore.updateData {data ->
            data.copy(fontSize = size)
        }
    }

    suspend fun saveDataSettings(settings: DataSettings) {
        context.protoDataStore.updateData {
            settings
        }
    }

    fun getSettings() = context.protoDataStore.data
}