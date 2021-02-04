package com.pants.data.repository

import com.pants.data.datasource.NoteApi
import com.pants.data.datasource.NoteCache
import com.pants.domain.Note
import com.pants.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEmpty

class NoteRepositoryImpl(
    private val cache: NoteCache,
    private val api: NoteApi
) : NoteRepository {

    override suspend fun getNote(id: String): Note? = cache.getNote(id)

    @ExperimentalCoroutinesApi
    override fun getNotes(): Flow<List<Note>> = cache.getNotes().onEmpty {
        val notes = api.getNotes()
        cache.saveNotes(notes)
    }

    override suspend fun saveNote(note: Note) {
        cache.saveNote(note)
        api.saveNote(note)
    }

    override suspend fun toggleFavourite(note: Note): Boolean = cache.toggleFavourite(note)
}