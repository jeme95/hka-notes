package com.example.hkaNotes.viewmodel.note

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.hkaNotes.database.Note
import com.example.hkaNotes.database.NoteViewModelRoom

class NoteStateHolder(
    private val viewModel: NoteViewModelRoom
) {
    val notes = viewModel.notes

    fun addNote(note: Note) = viewModel.addNote(note)
    fun currentNote(id: Long) = viewModel.getById(id)
    fun deleteById(id: Long) = viewModel.deleteById(id)
}

@Composable
fun rememberNoteScreenState(
    resources: Resources = LocalContext.current.resources,
    viewModel: NoteViewModelRoom
) = remember(resources, viewModel) {
    NoteStateHolder(viewModel)
}
