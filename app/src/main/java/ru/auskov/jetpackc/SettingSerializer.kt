package ru.auskov.jetpackc

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SettingSerializer: Serializer<DataSettings> {
    override val defaultValue: DataSettings
        get() = DataSettings()

    override suspend fun readFrom(input: InputStream): DataSettings {
        return try {
            Json.decodeFromString(
                deserializer = DataSettings.serializer(),
                string = input.readBytes().toString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            DataSettings()
        }
    }

    override suspend fun writeTo(t: DataSettings, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = DataSettings.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}