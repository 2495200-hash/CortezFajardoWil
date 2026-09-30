package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bibliotech.model.Prestamo


@Dao
interface PrestamoDao {

    // Aqui se hace el crud y metodos para trabajar con el ROOM

    @Insert
    fun insertar(prestamo: Prestamo)

    @Query("SELECT * FROM prestamos WHERE devuelto = 0")

    fun obtenerPrestamosActivos(): List<Prestamo>

    @Query("SELECT * FROM prestamos WHERE id = :id")
    fun obtenerPrestamoPorId(id: Int): Prestamo?




}