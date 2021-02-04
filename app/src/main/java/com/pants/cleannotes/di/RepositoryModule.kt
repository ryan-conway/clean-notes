package com.pants.cleannotes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pants.data.api.NoteApiImpl
import com.pants.data.cache.NoteCacheImpl
import com.pants.data.cache.adapter.NoteAdapter
import com.pants.data.cache.preferences.Preferences
import com.pants.data.cache.preferences.PreferencesImpl
import com.pants.data.cache.room.NoteDatabase
import com.pants.data.datasource.NoteApi
import com.pants.data.datasource.NoteCache
import com.pants.data.repository.NoteRepositoryImpl
import com.pants.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "notes.db")
            .build()
    }

    @Provides
    fun providePreferences(@ApplicationContext context: Context): Preferences =
        PreferencesImpl(context)

    @Provides
    fun provideNoteAdapter() = NoteAdapter()

    @Provides
    fun provideNoteCache(
        database: NoteDatabase,
        preferences: Preferences,
        adapter: NoteAdapter
    ): NoteCache =
        NoteCacheImpl(database, preferences, adapter)

    @Provides
    fun provideNoteApi(): NoteApi = NoteApiImpl()

    @Provides
    fun provideNoteRepository(cache: NoteCache, api: NoteApi): NoteRepository =
        NoteRepositoryImpl(cache, api)
}