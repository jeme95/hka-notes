package com.example.testapplication.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import com.example.testapplication.viewmodel.note.NoteDetailsViewModel
import com.example.testapplication.screens.CreateNoteScreen
import com.example.testapplication.screens.ListPreviewScreen
import com.example.testapplication.screens.NoteDetailsScreen
import com.example.testapplication.viewmodel.note.NoteViewModel


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NotesNavGraph(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel(),
    noteDetailsViewModel: NoteDetailsViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.ListPreviewRoute.route
){
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
        NoteDetailsScreen(noteDetailsViewModel) { navController.popBackStack() }
    }
}

}
