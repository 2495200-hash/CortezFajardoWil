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

    fun obtenerLibroPorId(id: Int): Libro? {
        return libroDao.obtenerLibroPorId(id)
    }

    //Funciones para actualizar y eliminar libros

    fun actualizarLibro(libro: Libro) {
        libroDao.actualizarLibro(libro)
    }

    fun eliminarLibro(libro: Libro) {
        libroDao.eliminarLibro(libro)
    }
}

