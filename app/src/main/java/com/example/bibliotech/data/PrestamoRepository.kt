package com.example.bibliotech.data

import com.example.bibliotech.model.Prestamo

class PrestamoRepository(private val prestamoDao: PrestamoDao) {
    fun insertarPrestamo(prestamo: Prestamo): Long {
        return prestamoDao.insertar(prestamo)
    }
    fun obtenerPrestamosActivos(): List<Prestamo> {
        return prestamoDao.obtenerPrestamosActivos()
    }
    fun obtenerPrestamoPorId(id: Int): Prestamo? {
        return prestamoDao.obtenerPrestamoPorId(id)
    }
    fun actualizarPrestamo(prestamo: Prestamo) {
        prestamoDao.actualizarPrestamo(prestamo)
    }

}