package com.example.thinkdo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thinkdo.model.Note
import com.example.thinkdo.repo.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel (private val repo : NoteRepository) : ViewModel() {

    val notes : StateFlow<List<Note>> =
        repo.getAll().stateIn(viewModelScope, SharingStarted.Lazily,emptyList())

    fun upsert(
        id: Int? = null,
        title: String,
        content: String,
        category: String,
        color: Int,
        isPinned : Boolean
    ) = viewModelScope.launch {
        val now = System.currentTimeMillis()
        val note = if (id != null && id != 0){
            Note(id,title,content,category,color,isPinned, createAt = now, updateAt = now)
        }else{
            Note(title = title, content = content, category = category, color = color,
                isPinned = isPinned, updateAt = now)
        }
        if (id != null && id != 0) repo.update(note) else repo.insert(note)
    }
    fun delete(note: Note) = viewModelScope.launch { repo.delete(note) }
    }