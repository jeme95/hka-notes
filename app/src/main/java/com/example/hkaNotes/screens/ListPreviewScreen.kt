package com.example.hkaNotes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hkaNotes.ui.theme.AppFontColor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavHostController
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.navigation.Route
import com.example.hkaNotes.ui.theme.TestApplicationTheme
import com.example.hkaNotes.viewmodel.note.NoteListItemComposable
import com.example.hkaNotes.viewmodel.note.rememberNoteScreenState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ListPreviewScreen(
    navController: NavHostController,
    viewModel: NoteViewModelRoom
) {

    val stateHolder = rememberNoteScreenState(viewModel = viewModel)
    val notes by stateHolder.notes.observeAsState(emptyList())

    fun navigateToNoteDetails(id: Long) {
        navController.navigate(Route.NoteDetailsRoute.route + "/" + id)
    }


    fun navigateToCreateNote() {
        navController.navigate(Route.CreateNoteRoute.route)
    }


    @Composable
    fun ListNotesComposable() {
        LazyColumn(
            verticalArrangement = Arrangement.Center
        ) {


            items(
                items = notes,
                key = { note ->
                    note.id
                }
            ) { note ->
                NoteListItemComposable(
                    note = note,
                    { navigateToNoteDetails(note.id) }, viewModel
                )
            }
        }
    }



    TestApplicationTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Notes", color = AppFontColor) }) },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                AddButton { navigateToCreateNote() }
            },
        ) {
            if (notes.isEmpty()) {
                NoNotesNotice()
            }
            ListNotesComposable()
        }
    }


}


@Composable
private fun NoNotesNotice() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "no notes", textAlign = TextAlign.Center)
    }
}


@Composable
private fun AddButton(createNote: () -> Unit) {
    FloatingActionButton(onClick = createNote) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add",
            tint = AppFontColor


        )
    }
}


























