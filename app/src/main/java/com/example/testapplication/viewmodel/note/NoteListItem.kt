package com.example.testapplication.viewmodel.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.AppFontColor
import com.example.testapplication.ui.theme.WarningColor

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NoteListItem(
    note: Note,
    showNoteDetails: () -> Unit,
    noteDetailsViewModel: NoteDetailsViewModel,
    viewModel: NoteViewModel,
    modifier: Modifier = Modifier
) {

    var openDialog by rememberSaveable { mutableStateOf(false) }

    var showRemoveIcon by remember { mutableStateOf(false) }

    fun showRemoveIcon() {
        showRemoveIcon = true
    }

    fun openNote() {
        noteDetailsViewModel.changeTitle(note.title)
        noteDetailsViewModel.changeContent(note.content)
        showNoteDetails()
    }

    fun removeNote() {
        viewModel.deleteNote(note)
        showRemoveIcon = false
    }

    @Composable
    fun RemoveIcon() {
        DropdownMenu(
            expanded = showRemoveIcon,
            onDismissRequest = { showRemoveIcon = false }
        ) {
            IconButton(
                onClick = { removeNote() },
                modifier = Modifier.background(Color.Transparent)

            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = WarningColor,
                    modifier = Modifier.background(Color.Transparent)

                )
            }
        }
    }

    @Composable
    fun NoteTitle() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val truncatedTitle = checkTitleLength(note.title)

            Text(text = truncatedTitle, fontSize = MaterialTheme.typography.h5.fontSize)


        }
    }

    @Composable
    fun NoteCard() {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp, 5.dp, 10.dp, 5.dp)
                .background(Color.White)
                .combinedClickable(
                    onClick = {
                        openNote()
                    },
                    onLongClick = {
                        showRemoveIcon()
                    }),
            elevation = 10.dp,
            shape = RoundedCornerShape(5.dp),

            ) {

            NoteTitle()
            RemoveIcon()

        }
    }

    @Composable
    fun DeleteConfirmationDialog() {
        Column {
            if (openDialog) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    title = {
                        Text(text = "Delete confirmation")
                    },
                    text = {
                        Text("Are you sure you want to delete this item?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.deleteNote(note)
//                          removeNote()
                                openDialog = false
                            }) {
                            Text("Delete", color = AppFontColor)
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog = false
                            }) {
                            Text("Cancel", color = AppFontColor)
                        }
                    }
                )

            }
        }

    }

    NoteCard()
    DeleteConfirmationDialog()


}


/***
 *   Method to check the length of a string and truncate it if it is greater than 10
 */
fun checkTitleLength(title: String): String {
    return if (title.length > 10) {
        "${title.substring(0, title.length.coerceAtMost(10))}..."
    } else {
        title
    }
}




