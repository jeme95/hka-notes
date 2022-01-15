package com.example.testapplication.viewmodel.note

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class NoteStateHolder(
    val resources: Resources,
    val viewModel: NoteViewModel
) {
    val notes = viewModel.notes

    fun addNote(title:String, content:String) = viewModel.addNote(title,content)
}

@Composable
fun rememberNoteScreenState(
    resources: Resources = LocalContext.current.resources,
    viewModel: NoteViewModel
) = remember(resources, viewModel) {
    NoteStateHolder(resources, viewModel)
}
