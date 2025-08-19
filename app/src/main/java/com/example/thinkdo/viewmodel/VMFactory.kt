package com.example.thinkdo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thinkdo.database.NoteDatabase
import com.example.thinkdo.repo.NoteRepository

class VMFactory (context: Context) : ViewModelProvider.Factory {
    private val repo = NoteRepository(NoteDatabase.get(context).noteDao())

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown VM class")
    }
}