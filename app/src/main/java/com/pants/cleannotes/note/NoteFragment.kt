package com.pants.cleannotes.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pants.cleannotes.MainActivityViewModel
import com.pants.cleannotes.databinding.FragmentNoteBinding
import com.pants.cleannotes.util.NoteTextWatcher
import com.pants.domain.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val viewModel: NoteViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private val args: NoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.setFabClickListener(null)

        val textWatcher = NoteTextWatcher { viewModel.markAsEdited() }

        viewModel.note.observe(viewLifecycleOwner) {
            it?.let {
                removeTextWatchers(textWatcher)
                setNote(it)
                setTextWatchers(textWatcher)
            }
        }
        setTextWatchers(textWatcher)
        viewModel.loadNote(args.noteId)
    }

    private fun setNote(note: Note) {
        binding.title.setText(note.title)
        binding.text.setText(note.text)
    }

    private fun setTextWatchers(textWatcher: NoteTextWatcher) {
        binding.title.addTextChangedListener(textWatcher)
        binding.text.addTextChangedListener(textWatcher)
    }

    private fun removeTextWatchers(textWatcher: NoteTextWatcher) {
        binding.title.removeTextChangedListener(textWatcher)
        binding.text.removeTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveNote(binding.title.text.toString(), binding.text.text.toString())
    }
}