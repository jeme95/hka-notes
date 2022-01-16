package com.example.hkaNotes.viewmodel.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hkaNotes.database.Note
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.ui.theme.WarningColor

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NoteListItemComposable(
    note: Note,
    showNoteDetails: () -> Unit,
    viewModel: NoteViewModelRoom,
    modifier: Modifier = Modifier
) {

    var showRemoveIcon by remember { mutableStateOf(false) }
    val stateHolder = rememberNoteScreenState(viewModel = viewModel)

    fun deleteNote() {
        stateHolder.deleteById(note.id)
        showRemoveIcon = false
    }

    @Composable
    fun RemoveIcon() {
        DropdownMenu(
            expanded = showRemoveIcon,
            onDismissRequest = { showRemoveIcon = false }
        ) {
            IconButton(
                onClick = { deleteNote() },
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
                        showNoteDetails()
                    },
                    onLongClick = {
                        showRemoveIcon = true
                    }),
            elevation = 10.dp,
            shape = RoundedCornerShape(5.dp),

            ) {

            NoteTitle()
            RemoveIcon()

        }
    }

    NoteCard()

}


/***
 *   Method to check the length of a string and truncate it if it is greater than 10
 */
fun checkTitleLength(title: String): String {
    return if (title.length > 20) {
        "${title.substring(0, title.length.coerceAtMost(20))}..."
    } else {
        title
    }
}




