package com.example.bibliotech.data


import com.example.bibliotech.model.Estudiante


class EstudianteRepository(


    private val estudianteDao: EstudianteDao


) {


    // =========================
    // CREATE
    // Inserta un nuevo estudiante
    // =========================
    fun insertarEstudiante(estudiante: Estudiante): Long {
        return estudianteDao.insertarEstudiante(estudiante)
    }


    // =========================
    // READ
    // Obtiene todos los estudiantes
    // =========================
    fun obtenerEstudiantes(): List<Estudiante> {
        return estudianteDao.obtenerEstudiantes()
    }


    // =========================
    // READ
    // Obtiene un estudiante por su ID
    // =========================
    fun obtenerEstudiantePorId(id: Int): Estudiante? {
        return estudianteDao.obtenerEstudiantePorId(id)
    }


    // =========================
    // UPDATE
    // Actualiza la información del estudiante
    // =========================
    fun actualizarEstudiante(estudiante: Estudiante) {
        estudianteDao.actualizarEstudiante(estudiante)
    }


    // =========================
    // DELETE
    // Elimina un estudiante
    // =========================
    fun eliminarEstudiante(estudiante: Estudiante) {
        estudianteDao.eliminarEstudiante(estudiante)
    }


}
