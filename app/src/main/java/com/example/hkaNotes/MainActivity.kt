package com.example.hkaNotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import com.example.hkaNotes.database.NoteViewModelFactory
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.navigation.NotesNavGraph
import com.example.hkaNotes.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteRepo = (application as MyApplication).noteRepo
        val noteViewModel: NoteViewModelRoom by viewModels { NoteViewModelFactory(noteRepo) }

        setContent {
            TestApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NotesNavGraph(noteViewModel)
                }
            }
        }
    }


}


