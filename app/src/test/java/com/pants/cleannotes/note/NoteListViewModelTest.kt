package com.pants.cleannotes.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pants.cleannotes.testutil.TestCoroutineScopeRule
import com.pants.cleannotes.testutil.getOrAwaitValue
import com.pants.cleannotes.util.DispatcherProvider
import com.pants.domain.Note
import com.pants.domain.usecase.DeleteNoteUseCase
import com.pants.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class NoteListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val testScopeRule = TestCoroutineScopeRule()

    private val dispatcherProvider = mock(DispatcherProvider::class.java)
    private val getNotesUseCase = mock(GetNotesUseCase::class.java)
    private val deleteNotesUseCase = mock(DeleteNoteUseCase::class.java)

    @Before
    fun setup() {
        `when`(dispatcherProvider.getIO()).thenReturn(testScopeRule.testDispatcher)
    }

    @Test
    fun `should have list`() = testScopeRule.runBlockingTest {
        val expected = emptyList<Note>()
        `when`(getNotesUseCase.execute()).thenReturn(flowOf(expected))
        val viewModel = NoteListViewModel(getNotesUseCase, deleteNotesUseCase, dispatcherProvider)

        val actual = viewModel.notes.getOrAwaitValue()

        Mockito.verify(getNotesUseCase, Mockito.times(1)).execute()
        assertEquals(expected, actual)
    }

    @Test
    fun `list should be empty`() = testScopeRule.runBlockingTest {
        val expected = emptyList<Note>()
        `when`(getNotesUseCase.execute()).thenReturn(flowOf(expected))
        val viewModel = NoteListViewModel(getNotesUseCase, deleteNotesUseCase, dispatcherProvider)

        val actual = viewModel.notes.getOrAwaitValue()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `list should not be empty`() = testScopeRule.runBlockingTest {
        val note = mock(Note::class.java)
        val expected = listOf(note)
        `when`(getNotesUseCase.execute()).thenReturn(flowOf(expected))
        val viewModel = NoteListViewModel(getNotesUseCase, deleteNotesUseCase, dispatcherProvider)

        val actual = viewModel.notes.getOrAwaitValue()

        assertTrue(actual.isNotEmpty())
    }

}