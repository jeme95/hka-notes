package com.example.testapplication.viewmodel.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    private var _notes: MutableLiveData<List<Note>> =
        MutableLiveData(listOf())
    val notes: LiveData<List<Note>> = _notes

    fun addNote( title:String, content:String) {
        _notes.value = _notes.value?.plus(Note(title, content))
    }

    fun deleteNote(note: Note){
        _notes.value = _notes.value?.minus(Note(note.title, note.content))
    }

}
