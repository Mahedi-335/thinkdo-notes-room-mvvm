package com.example.thinkdo.repo

import com.example.thinkdo.dao.NoteDao
import com.example.thinkdo.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {

    fun getAll(): Flow<List<Note>> = dao.getAll()

    fun getNoteByID(id: Long): Flow<Note> = dao.getNoteById(id)

    suspend fun insert(note: Note) = dao.insert(note)

    suspend fun update(note: Note) = dao.update(note)

    suspend fun delete(note: Note) = dao.delete(note)
}