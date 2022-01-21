package com.example.hkaNotes

import android.app.Application
import com.example.hkaNotes.database.NoteRepository
import com.example.hkaNotes.database.NotesDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    private val dao by lazy { NotesDatabase.getInstance(this).noteDao() }
    val noteRepo by lazy { NoteRepository(dao) }
}
