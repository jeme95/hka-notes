package com.example.hkaNotes.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.hkaNotes.viewmodel.note.rememberNoteScreenState
import com.example.hkaNotes.R
import com.example.hkaNotes.database.Note
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.ui.theme.AppFontColor
import com.example.hkaNotes.ui.theme.TestApplicationTheme
import com.example.hkaNotes.viewmodel.note.CreateNoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreateNoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModelRoom
) {

    val createNoteViewModel: CreateNoteViewModel = viewModel()

    val title by createNoteViewModel.title.observeAsState()
    val content by createNoteViewModel.content.observeAsState()

    val openDialogEmptyNote by createNoteViewModel.openDialogEmptyNote.observeAsState()
    val openDialogMaxLengthReached by createNoteViewModel.openDialogMaxLengthReached.observeAsState()

    val stateHolder = rememberNoteScreenState(viewModel = viewModel)


    fun navigateBack() {
        navController.popBackStack()
    }


    @Composable
    fun SaveButton() {
        IconButton(onClick = {

            if (createNoteViewModel.checkBlankAndSave(title, content)) {
                stateHolder.addNote(Note(title!!, content!!))
                navigateBack()
            } else {
                createNoteViewModel.openDialogEmptyNote()
            }


        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_black_24dp),
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
                    value = title ?: "",
                    placeholder = {
                        Text(text = "title", color = Color.LightGray)
                    },
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 40.dp, end = 40.dp)
                        .verticalScroll(enabled = true, state = ScrollState(100))
                        .fillMaxWidth(), onValueChange = {
                        if (it.length <= 100) {
                            createNoteViewModel.changeTitle(it)
                        } else createNoteViewModel.openDialogMaxLengthReached()
                    })
//                TitleComposable_end


                Spacer(modifier = Modifier.width(20.dp))

                //                ContentComposable_start
                OutlinedTextField(value = content ?: "",
                    modifier = Modifier
                        .height(220.dp)
                        .verticalScroll(
                            enabled = true,
                            state = ScrollState(2 * (content?.length ?: 0))
                        )
                        .padding(bottom = 5.dp, start = 40.dp, end = 40.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "content", color = Color.LightGray)
                    },
                    onValueChange = { createNoteViewModel.changeContent(it) })
                //                ContentComposable_end
            }

//            EmptyNoteDialog_start
            Column {
                if (openDialogEmptyNote == true) {
                    AlertDialog(
                        onDismissRequest = {
                            createNoteViewModel.closeDialogEmptyNote()
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
                                    createNoteViewModel.closeDialogEmptyNote()
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
            if (openDialogMaxLengthReached == true) {
                AlertDialog(
                    onDismissRequest = {
                        createNoteViewModel.closeDialogMaxLengthReached()
                    },
                    title = {
                        Text(text = "Failure")
                    },
                    text = {
                        Text("Title may be a maximum of 100 characters!")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                createNoteViewModel.closeDialogMaxLengthReached()
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






