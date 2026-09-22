package com.example.bibliotech.data

import com.example.bibliotech.model.Libro

class LibroRepository(
    private val libroDao: LibroDao
) {
    fun insertarLibro(libro: Libro): Long {
        return libroDao.insertarLibro(libro)
    }

    fun obtenerLibros(): List<Libro> {
        return libroDao.obtenerLibros()
    }
}