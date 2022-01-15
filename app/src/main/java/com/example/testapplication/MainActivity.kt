package com.example.testapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import com.example.testapplication.navigation.NotesNavGraph
import com.example.testapplication.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG_MAIN, "creating...")
        setContent {
            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NotesNavGraph()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG_MAIN, "starting...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG_MAIN, "resuming...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG_MAIN, "pausing...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG_MAIN, "stopping...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG_MAIN, "destroying...")
    }

    companion object {
        const val LOG_TAG_MAIN = "MainActivity"
    }

}


private const val LOG_TAG = "UiControlElements"
