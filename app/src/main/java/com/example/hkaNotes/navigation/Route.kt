package com.example.hkaNotes.navigation

sealed class Route(val route: String) {
    object ListPreviewRoute : Route("listPreview")
    object CreateNoteRoute : Route("create")
    object NoteDetailsRoute : Route("noteDetails")


}
