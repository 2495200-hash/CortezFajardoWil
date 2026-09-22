package com.example.bibliotech.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibliotech.model.Libro

@Database(
    entities = [Libro::class],
    version = 1,
    exportSchema = false
)
abstract class BibliotecaDatabase : RoomDatabase() {
    abstract fun libroDao(): LibroDao
}