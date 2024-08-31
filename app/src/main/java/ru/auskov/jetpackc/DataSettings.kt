package ru.auskov.jetpackc

import kotlinx.serialization.Serializable
import ru.auskov.jetpackc.ui.theme.Blue

@Serializable
data class DataSettings(
    val fontSize: Int = 50,
    val bgColor: ULong = Blue.value
)
