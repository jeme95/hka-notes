package com.example.hkaNotes.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModelRoom(private val repository: NoteRepository) : ViewModel() {

    val notes: LiveData<List<Note>> = repository.readAllData.asLiveData()


     fun getById(id: Long): LiveData<Note> {
        return repository.getById(id)
    }

    fun addNote(noteItem: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(noteItem)
        }
    }

    fun deleteById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteById(id)
        }
    }


}



