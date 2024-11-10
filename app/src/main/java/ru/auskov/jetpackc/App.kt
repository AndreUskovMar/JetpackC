package ru.auskov.jetpackc

import android.app.Application
import ru.auskov.jetpackc.data.MainDb

class App: Application() {
    val database by lazy { MainDb.createDatabase(this) }
}