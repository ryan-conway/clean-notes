package com.pants.data.cache

import com.pants.data.cache.adapter.NoteAdapter
import com.pants.data.cache.preferences.Preferences
import com.pants.data.cache.room.NoteDatabase
import com.pants.data.datasource.NoteCache
import com.pants.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteCacheImpl(
    private val database: NoteDatabase,
    private val preferences: Preferences,
    private val adapter: NoteAdapter
): NoteCache {

    override suspend fun getNote(id: String): Note? = database.noteDao().getNote(id)?.let {
        adapter.toDomain(it, preferences.isFavourite(it.id))
    }

    override fun getNotes(): Flow<List<Note>> = database.noteDao().getNotes().map { list ->
        list.map { note -> adapter.toDomain(note, preferences.isFavourite(note.id)) }
    }

    override suspend fun saveNote(note: Note) {
        database.noteDao().saveNote(adapter.toEntity(note))
        toggleFavourite(note)
    }

    override suspend fun saveNotes(notes: List<Note>) {
        database.noteDao().saveNotes(notes.map { adapter.toEntity(it) })
        for (note in notes) {
            toggleFavourite(note)
        }
    }

    override suspend fun toggleFavourite(note: Note): Boolean = preferences.toggleFavourite(note.id)
}