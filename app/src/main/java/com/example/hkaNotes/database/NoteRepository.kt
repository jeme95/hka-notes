package com.example.hkaNotes.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val notesDAO: NotesDAO) {

    val readAllData: Flow<List<Note>> = notesDAO.getAll()

    suspend fun addNote(noteItem: Note) {
        notesDAO.insert(noteItem)
    }

    suspend fun deleteById(id: Long) {
        notesDAO.deleteById(id)
    }

     fun getById(id: Long): LiveData<Note> {
        return notesDAO.getById(id)
    }


}
