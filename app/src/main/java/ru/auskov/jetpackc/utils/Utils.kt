package ru.auskov.jetpackc.utils

fun roundToIntTemp(temp: String): String {
    return if (temp.isNotEmpty()) temp.toFloat().toInt().toString().plus("°C")
    else ""
}