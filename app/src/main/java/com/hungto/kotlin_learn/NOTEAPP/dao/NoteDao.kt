package com.hungto.kotlin_learn.NOTEAPP.dao

import androidx.room.*
import com.hungto.kotlin_learn.NOTEAPP.entities.Notes

@Dao
interface NoteDao {

    @Query("SELECT*FROM notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<Notes>

    @Query("SELECT*FROM notes where id=:id")
    suspend fun getSpecificNote(id: Int): Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun deleteSpecificNote(id: Int)

    @Update
    suspend fun updateNote(notes: Notes)
}