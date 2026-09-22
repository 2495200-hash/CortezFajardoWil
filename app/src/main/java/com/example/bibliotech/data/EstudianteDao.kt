package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bibliotech.model.Estudiante


@Dao
interface EstudianteDao {


    // =========================
    // CREATE
    // Inserta un nuevo estudiante
    // =========================
    @Insert
    fun insertarEstudiante(estudiante: Estudiante): Long


    // =========================
    // READ
    // Obtiene todos los estudiantes
    // =========================
    @Query("SELECT * FROM Estudiantes")
    fun obtenerEstudiantes(): List<Estudiante>


    // =========================
    // READ
    // Obtiene un estudiante por su ID
    // =========================
    @Query("SELECT * FROM Estudiantes WHERE id = :id")
    fun obtenerEstudiantePorId(id: Int): Estudiante?


    // =========================
    // UPDATE
    // Actualiza la información del estudiante
    // =========================
    @Update
    fun actualizarEstudiante(estudiante: Estudiante)


    // =========================
    // DELETE
    // Elimina un estudiante
    // =========================
    @Delete
    fun eliminarEstudiante(estudiante: Estudiante)
}

