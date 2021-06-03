package com.hungto.kotlin_learn.NOTEAPP.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hungto.kotlin_learn.NOTEAPP.dao.NoteDao
import com.hungto.kotlin_learn.NOTEAPP.entities.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){
    companion object {
        var noteDatabase: NoteDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): NoteDatabase {
            if (noteDatabase == null) {
                noteDatabase = Room.databaseBuilder(
                    context, NoteDatabase::class.java,
                    "note.db"
                ).build()
            }
            return noteDatabase!!
        }
    }

    abstract fun noteDao(): NoteDao
}