package com.example.hkaNotes.viewmodel.note


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateNoteViewModel : ViewModel(){


    private var _title: MutableLiveData<String> =
        MutableLiveData("")
    val title: LiveData<String> = _title

    private var _content: MutableLiveData<String> =
        MutableLiveData("")
    val content: LiveData<String> = _content


    private var _openDialogEmptyNote: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val openDialogEmptyNote: LiveData<Boolean> = _openDialogEmptyNote

    private var _openDialogMaxLengthReached: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val openDialogMaxLengthReached: LiveData<Boolean> = _openDialogMaxLengthReached

    fun changeTitle(newTitle:String) {
        _title.value = newTitle
    }

    fun changeContent(newContent:String) {
        _content.value = newContent
    }
    fun openDialogEmptyNote() {
        _openDialogEmptyNote.value = true
    }

    fun closeDialogEmptyNote() {
        _openDialogEmptyNote.value = false
    }

    fun openDialogMaxLengthReached() {
        _openDialogMaxLengthReached.value = true
    }
    fun closeDialogMaxLengthReached() {
        _openDialogMaxLengthReached.value = false
    }

    fun checkBlankAndSave(title: String?, content: String?):Boolean {
                return (title?.isNotBlank()?: false) && (content?.isNotBlank() ?: false)
    }

}
