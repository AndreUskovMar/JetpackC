package ru.auskov.jetpackc

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.auskov.jetpackc.data.MainDb
import ru.auskov.jetpackc.data.NameEntity

class MainViewModel(private val database: MainDb) : ViewModel() {
    val listItems = database.dao.getAllItems()
    val text = mutableStateOf("")
    var nameEntity: NameEntity? = null
    fun insertItem() = viewModelScope.launch {
        val nameItem = nameEntity?.copy(name = text.value)
            ?: NameEntity(name = text.value)

        database.dao.insertItem(nameItem)
        text.value = ""
        nameEntity = null
    }

    fun deleteItem(item: NameEntity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = checkNotNull(extras[APPLICATION_KEY] as App).database
                return MainViewModel(database) as T
            }
        }
    }
}