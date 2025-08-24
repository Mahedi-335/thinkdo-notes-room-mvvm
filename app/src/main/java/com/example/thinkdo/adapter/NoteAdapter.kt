package com.example.thinkdo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thinkdo.model.Note
import com.example.thinkdo.databinding.ItemNoteBinding

class NoteAdapter(private val onClick: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF) {

    object DIFF : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }

    inner class NoteViewHolder(private val b: ItemNoteBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(note: Note) = with(b) {
            titleText.text = note.title
            contentText.text = note.content
            (root as View).background?.setTint(note.color)
            root.setOnClickListener { onClick(note) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.NoteViewHolder {
        val b = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(b)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) = holder.bind(getItem(position))
}