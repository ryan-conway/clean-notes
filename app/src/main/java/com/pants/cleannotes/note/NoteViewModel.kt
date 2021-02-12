package com.pants.cleannotes.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pants.cleannotes.util.DispatcherProvider
import com.pants.domain.Note
import com.pants.domain.usecase.GetNoteUseCase
import com.pants.domain.usecase.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _note = MutableLiveData<Note?>()
    val note: LiveData<Note?>
        get() = _note

    private var isEdited: Boolean = false
    private var noteId: String? = null

    fun markAsEdited() {
        isEdited = true
    }

    fun loadNote(id: String?) = viewModelScope.launch {
        withContext(dispatchers.getIO()) {
            noteId = id
            id?.let { _note.postValue(getNoteUseCase.execute(it)) }
        }
    }

    fun saveNote(title: String, text: String) = GlobalScope.launch {
        withContext(dispatchers.getIO()) {
            if (!isEdited) return@withContext
            val noteId = noteId
            if (noteId == null && title.isBlank() && text.isBlank()) return@withContext
            val note = Note(
                id = noteId ?: createId(),
                title = title,
                text = text,
                modifiedDate = Date().time,
                isFavourite = false
            )
            saveNoteUseCase.execute(note)
        }
    }

    private fun createId(): String = UUID.randomUUID().toString()
}