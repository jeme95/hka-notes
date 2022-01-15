package com.example.hkaNotes.viewmodel.note

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.hkaNotes.database.Note
import com.example.hkaNotes.database.NoteViewModelRoom

class NoteStateHolder(
    val resources: Resources,
    val viewModel: NoteViewModelRoom
) {
    val notes = viewModel.notes

    fun addNote(note: Note) = viewModel.addNote(note)
}

@Composable
fun rememberNoteScreenState(
    resources: Resources = LocalContext.current.resources,
    viewModel: NoteViewModelRoom
) = remember(resources, viewModel) {
    NoteStateHolder(resources, viewModel)
}