package com.pants.cleannotes.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pants.cleannotes.databinding.ItemNoteBinding
import com.pants.domain.Note

class NoteListAdapter(
    private val callback: (Note) -> Unit,
    private val deleteCallback: (Note) -> Unit
) : ListAdapter<Note, NoteListViewHolder>(NoteDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder =
        NoteListViewHolder.from(parent)

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) =
        holder.bind(getItem(position), callback, deleteCallback)
}

class NoteListViewHolder private constructor(
    private val binding: ItemNoteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Note?, callback: (Note) -> Unit, deleteCallback: (Note) -> Unit) {
        item?.let { note ->
            binding.note = note
            binding.root.setOnClickListener { callback(note) }
            binding.root.setOnLongClickListener {
                deleteCallback(note)
                return@setOnLongClickListener true
            }
        }
    }

    companion object {

        fun from(parent: ViewGroup) = NoteListViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}