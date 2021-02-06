package com.pants.cleannotes.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pants.cleannotes.MainActivityViewModel
import com.pants.cleannotes.databinding.FragmentNoteListBinding
import com.pants.domain.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel: NoteListViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.setLoading(true)
        activityViewModel.setFabClickListener { createNote() }

        val adapter = NoteListAdapter(
            ::viewNote,
            ::deleteNote
        )
        binding.recycler.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner) {
            it?.let { list ->
                adapter.submitList(list)
                activityViewModel.setLoading(false)
                binding.isEmpty = it.isEmpty()
            }
        }
    }

    private fun createNote() = viewNote(null)

    private fun viewNote(note: Note?) = findNavController().navigate(
        NoteListFragmentDirections.actionToNotesFragment(note?.id)
    )

    private fun deleteNote(note: Note) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete ${note.title}?")
                .setNegativeButton("cancel", null)
                .setPositiveButton("delete") { dialog, _ ->
                    viewModel.deleteNote(note)
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }
}