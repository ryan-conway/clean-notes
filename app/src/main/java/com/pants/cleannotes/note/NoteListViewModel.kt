package com.pants.cleannotes.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pants.cleannotes.util.DispatcherProvider
import com.pants.domain.Note
import com.pants.domain.usecase.DeleteNoteUseCase
import com.pants.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val notes: LiveData<List<Note>> = getNotesUseCase.execute().asLiveData()

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            withContext(dispatchers.getIO()) {
                deleteNoteUseCase.execute(note)
            }
        }
    }
}