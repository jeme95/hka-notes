package com.example.testapplication.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.testapplication.viewmodel.note.NoteDetailsViewModel
import com.example.testapplication.ui.theme.AppFontColor

@Composable
fun NoteDetailsScreen(
    noteDetailsViewModel: NoteDetailsViewModel,
    goBack: () -> Unit
) {

    val title by noteDetailsViewModel.title.observeAsState()
    val content by noteDetailsViewModel.content.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ArrowBackIconComposable(goBack)
                }
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(enabled = true, state = ScrollState(10))
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
            painter = painterResource(id = com.example.testapplication.R.drawable.baseline_arrow_back_ios_black_24dp),
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
