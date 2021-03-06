package com.example.hkaNotes.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.ui.theme.AppFontColor
import com.example.hkaNotes.ui.theme.WarningColor
import com.example.hkaNotes.viewmodel.note.rememberNoteScreenState

@Composable
fun NoteDetailsScreen(
    id: String,
    viewModel: NoteViewModelRoom,
    goBack: () -> Unit
) {
    val idLong = id.toLong()

    val stateHolder = rememberNoteScreenState(viewModel = viewModel)
    val currentNote by stateHolder.currentNote(idLong).observeAsState()

    val title = currentNote?.title
    val content = currentNote?.content

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ArrowBackIconComposable(goBack)
                },
                actions = {
                    IconButton(onClick = {
                        stateHolder.deleteById(idLong)
                        goBack()
                    }) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "Delete Note",
                            tint = WarningColor
                        )
                    }
                }
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(enabled = true, state = ScrollState(10)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteTitleComposable(title)
            Spacer(modifier = Modifier.padding(20.dp))
            NoteContentComposable(content)
        }
    }


}

@Composable
fun ArrowBackIconComposable(goBack: () -> Unit) {
    IconButton(
        onClick = goBack

    ) {
        Icon(
            painter = painterResource(id = com.example.hkaNotes.R.drawable.baseline_arrow_back_ios_black_24dp),
//                                Icons.Filled.Done,           // alternative for back_arrow
            contentDescription = "Save",
            tint = AppFontColor
        )
    }
}

@Composable
fun NoteContentComposable(content: String?) {
    Text(text = "$content", modifier = Modifier.padding(start = 22.dp))
}

@Composable
fun NoteTitleComposable(title: String?) {
    Text(
        text = "$title",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
    )
}
