package com.example.hkaNotes.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.hkaNotes.viewmodel.note.rememberNoteScreenState
import com.example.hkaNotes.R
import com.example.hkaNotes.database.Note
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.ui.theme.AppFontColor
import com.example.hkaNotes.ui.theme.TestApplicationTheme

@Composable
fun CreateNoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModelRoom
) {

    fun navigateBack() {
        navController.popBackStack()
    }

    var title: String by rememberSaveable { mutableStateOf("") }
    var content: String by rememberSaveable { mutableStateOf("") }

    var openDialogEmptyNote by rememberSaveable { mutableStateOf(false) }
    var openDialogMaxLengthReached by rememberSaveable { mutableStateOf(false) }

    val stateHolder = rememberNoteScreenState(viewModel = viewModel)

    fun checkBlankAndSave(title: String, content: String) {
        if (title.isNotBlank() && content.isNotBlank()) {
            stateHolder.addNote(Note(title, content))
            navigateBack()
        } else {
            openDialogEmptyNote = true
        }
    }

/*

// Save note even if back is clicked?
BackHandler {
        checkBlankAndSave(title, content)
    }*/

    @Composable
    fun SaveButton() {
        IconButton(onClick = {

            checkBlankAndSave(title, content)

        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_black_24dp),
//                                Icons.Filled.Done,           // alternative for back_arrow
                contentDescription = "Save",
                tint = AppFontColor
            )
        }
    }

    TestApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        SaveButton()
                    }
                )
            }

        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Spacer(modifier = Modifier.size(10.dp))

//                TitleComposable_start
                OutlinedTextField(
                    value = title,
                    placeholder = {
                        Text(text = "titel", color = Color.LightGray)
                    },
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 40.dp, end = 40.dp)
                        .verticalScroll(enabled = true, state = ScrollState(10))
                        .fillMaxWidth(), onValueChange = { if(it.length <= 120) title = it else openDialogMaxLengthReached = true})
//                TitleComposable_end


                Spacer(modifier = Modifier.width(20.dp))

                //                ContentComposable_start
                OutlinedTextField(value = content,
                    modifier = Modifier
                        .height(250.dp)
                        .verticalScroll(enabled = true, state = ScrollState(10))
                        .padding(bottom = 5.dp, start = 40.dp, end = 40.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "content", color = Color.LightGray)
                    },
                    onValueChange = { content = it })
                //                ContentComposable_end
            }

//            EmptyNoteDialog_start
            Column {
                if (openDialogEmptyNote) {
                    AlertDialog(
                        onDismissRequest = {
                            openDialogEmptyNote = false
                        },
                        title = {
                            Text(text = "Failure")
                        },
                        text = {
                            Text("Empty note can't be created!")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    openDialogEmptyNote = false
                                }) {
                                Text("Ok", color = AppFontColor)
                            }
                        }
                    )

                }
            }
//            EmptyNoteDialog_end

        }

        //            MaxLengthReachedDialog_start
        Column {
            if (openDialogMaxLengthReached) {
                AlertDialog(
                    onDismissRequest = {
                        openDialogMaxLengthReached = false
                    },
                    title = {
                        Text(text = "Failure")
                    },
                    text = {
                        Text("Title may be a maximum of 120 characters!")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialogMaxLengthReached = false
                            }) {
                            Text("Ok", color = AppFontColor)
                        }
                    }
                )

            }
        }
//            MaxLengthReachedDialog_end

    }


}






