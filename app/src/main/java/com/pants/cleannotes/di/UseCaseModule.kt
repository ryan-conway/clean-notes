package com.pants.cleannotes.di

import com.pants.domain.repository.NoteRepository
import com.pants.domain.usecase.GetNoteUseCase
import com.pants.domain.usecase.GetNotesUseCase
import com.pants.domain.usecase.SaveNoteUseCase
import com.pants.domain.usecase.ToggleFavouriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetNoteUseCase(repository: NoteRepository) = GetNoteUseCase(repository)

    @Provides
    fun provideGetNotesUseCase(repository: NoteRepository) = GetNotesUseCase(repository)

    @Provides
    fun provideSaveNoteUseCase(repository: NoteRepository) = SaveNoteUseCase(repository)

    @Provides
    fun provideToggleFavouriteUseCase(repository: NoteRepository) = ToggleFavouriteUseCase(repository)
}