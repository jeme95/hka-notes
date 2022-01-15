package com.example.testapplication.navigation

sealed class Route(val route: String) {
    object ListPreviewRoute : Route("listPreview")
    object CreateNoteRoute : Route("create")
    object NoteDetailsRoute : Route("note_details")


}
