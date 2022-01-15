package com.example.hkaNotes.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModelRoom(application: Application) : AndroidViewModel(application) {

    val notes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDAO = NotesDatabase.getInstance(application).noteDao()
        repository = NoteRepository(noteDAO)
        notes = repository.readAllData
    }

    fun addNote(noteItem: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(noteItem)
        }
    }

    fun deleteNote(noteItem: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(noteItem = noteItem)
        }

    }

    fun deleteById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteById(id)
        }
    }


}

class NoteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(NoteViewModelRoom::class.java)) {
            return NoteViewModelRoom(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


