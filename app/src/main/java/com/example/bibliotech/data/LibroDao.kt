package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bibliotech.model.Libro

@Dao
interface LibroDao {

    // fucion para insertar un libro en la base de datos
    @Insert
    fun insertarLibro(libro: Libro): Long

    // funcion para obtener todos los libros de la base de datos
    @Query("SELECT * FROM libros")
    fun obtenerLibros(): List<Libro>

    //funcion pra traer libros en base al id *READ*
    @Query("SELECT * FROM libros WHERE id = :id")
    fun obtenerLibroPorId(id: Int): Libro?

    //FUNCION PARA ACTUALIZAR UN LIBRO
    @Update
    fun actualizarLibro(libro: Libro)

    //FUNCION PARA ELIMINAR UN LIBRO
    @Delete
    fun eliminarLibro(libro: Libro)



}