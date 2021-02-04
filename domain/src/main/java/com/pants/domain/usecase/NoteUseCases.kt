package com.pants.domain.usecase

import com.pants.domain.Note
import com.pants.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(id: String): Note? = noteRepository.getNote(id)
}

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(): Flow<List<Note>> = noteRepository.getNotes()
}

class SaveNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(note: Note): Unit = noteRepository.saveNote(note)
}

class ToggleFavouriteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(note: Note): Boolean = noteRepository.toggleFavourite(note)
}