package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliotech.model.Libro

@Dao
interface LibroDao {
    @Insert
    fun insertarLibro(libro: Libro): Long

    @Query("SELECT * FROM libros")
    fun obtenerLibros(): List<Libro>
}