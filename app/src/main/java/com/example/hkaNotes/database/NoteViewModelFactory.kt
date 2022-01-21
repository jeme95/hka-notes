package com.example.hkaNotes.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(NoteViewModelRoom::class.java)) {
            return NoteViewModelRoom(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
