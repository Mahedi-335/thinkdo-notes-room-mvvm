package com.example.thinkdo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity create a database table
@Entity(tableName = "notes")
data class Note(

    // PrimaryKey make unique ID for every row
    @PrimaryKey(autoGenerate = true)
    // Unique ID
    val id: Int = 0,

    // Note title
    val title: String,
    // Note Content
    val content: String,
    // Select which category note is it
    val category: String,
    // Select Note color
    val color: Int,
    // Check note is pinned or not
    val isPinned: Boolean = false,
    // Note created time
    val createAt: Long = System.currentTimeMillis(),
    // Note update time
    val updateAt: Long = System.currentTimeMillis()
)
