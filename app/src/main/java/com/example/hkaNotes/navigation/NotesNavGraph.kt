package com.example.hkaNotes.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hkaNotes.database.NoteViewModelRoom
import com.example.hkaNotes.screens.CreateNoteScreen
import com.example.hkaNotes.screens.ListPreviewScreen
import com.example.hkaNotes.screens.NoteDetailsScreen


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NotesNavGraph(
    viewModel: NoteViewModelRoom,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.ListPreviewRoute.route,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(Route.ListPreviewRoute.route) {
            ListPreviewScreen(navController = navController, viewModel = viewModel)
        }



        composable(Route.CreateNoteRoute.route) {
            CreateNoteScreen(navController = navController, viewModel = viewModel)
        }


        composable(
            route = Route.NoteDetailsRoute.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            NoteDetailsScreen(
                id = backStackEntry.arguments?.getString("id")!!,
                viewModel = viewModel,
            ) { navController.popBackStack() }
        }
    }

}
