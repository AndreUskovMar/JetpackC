package ru.auskov.jetpackc

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import ru.auskov.jetpackc.ui.theme.Blue

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {
    suspend fun saveDataSettings(settings: DataSettings) {
        context.dataStore.edit { prefs ->
            prefs[intPreferencesKey("font_size")] = settings.fontSize
            prefs[longPreferencesKey("bg_color")] = settings.bgColor.toLong()
        }
    }

    fun getSettings() = context.dataStore.data.map { prefs ->
        return@map DataSettings(
            prefs[intPreferencesKey("font_size")] ?: 40,
            prefs[longPreferencesKey("bg_color")]?.toULong() ?: Blue.value
        )
    }
}