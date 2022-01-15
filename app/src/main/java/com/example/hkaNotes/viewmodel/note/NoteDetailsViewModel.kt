package com.example.hkaNotes.viewmodel.note

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

    private var _id: MutableLiveData<Long> =
        MutableLiveData(0)
    val id: LiveData<Long> = _id

/*    fun changeTitle(newTitle:String) {
        _title.value = newTitle;
    }

    fun changeContent(newContent:String) {
        _content.value = newContent;
    }

    fun changeID(id:Long) {
        _id.value = id;
    }*/

    fun changeNoteDetails(id:Long, newTitle:String, newContent:String){
        _id.value = id;
        _content.value = newContent;
        _title.value = newTitle;
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
