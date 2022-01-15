package com.example.hkaNotes.navigation

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import com.example.hkaNotes.database.NoteViewModelFactory
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.viewmodel.note.NoteDetailsViewModel
import com.example.hkaNotes.screens.CreateNoteScreen
import com.example.hkaNotes.screens.ListPreviewScreen
import com.example.hkaNotes.screens.NoteDetailsScreen


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NotesNavGraph(
    modifier: Modifier = Modifier,
    noteDetailsViewModel: NoteDetailsViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.ListPreviewRoute.route,
){
    val context = LocalContext.current
    val viewModel: NoteViewModelRoom = viewModel(factory = NoteViewModelFactory(context.applicationContext as Application))

    NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier
){

    composable(Route.ListPreviewRoute.route) {
        ListPreviewScreen (navController, viewModel,noteDetailsViewModel)
    }



    composable(Route.CreateNoteRoute.route) {
        CreateNoteScreen(navController,viewModel)
    }


    composable(
        route = Route.NoteDetailsRoute.route,
    ) {
        NoteDetailsScreen(noteDetailsViewModel, viewModel) { navController.popBackStack() }
    }
}

}
