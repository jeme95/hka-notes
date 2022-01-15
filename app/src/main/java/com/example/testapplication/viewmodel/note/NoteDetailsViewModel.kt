package com.example.testapplication.viewmodel.note

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteDetailsViewModel : ViewModel() {

    private var _title: MutableLiveData<String> =
        MutableLiveData("")
    val title: LiveData<String> = _title

    private var _content: MutableLiveData<String> =
        MutableLiveData("")
    val content: LiveData<String> = _content

    fun changeTitle(newTitle:String) {
        _title.value = newTitle;
        Log.i("NoteDetailsViewModel","_title: ${_title.value} , title: ${title.value}")
    }

    fun changeContent(newContent:String) {
        _content.value = newContent;
        Log.i("NoteDetailsViewModel","_content: ${_content.value} , content: ${content.value}")
    }

    init {
        Log.e(LOG_TAG, "Created")
    }

    override fun onCleared() {
        Log.e(LOG_TAG, "Cleared")

        super.onCleared()
    }

    companion object {
        const val LOG_TAG = "NoteDetailsViewModel"
    }
}
