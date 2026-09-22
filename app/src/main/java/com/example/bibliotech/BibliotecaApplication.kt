package com.example.bibliotech


import android.app.Application
import com.example.bibliotech.data.BibliotecaDatabase
import com.example.bibliotech.data.DatabaseProvider


// Repositories
import com.example.bibliotech.data.LibroRepository
import com.example.bibliotech.data.EstudianteRepository


class BibliotecaApplication : Application() {


    // =========================
    // BASE DE DATOS
    // =========================
    val database: BibliotecaDatabase by lazy {
        DatabaseProvider.getDatabase(this)
    }


    // =========================
    // DAO DE LIBROS
    // =========================
    val libroDao
        get() = database.libroDao()


    // =========================
    // DAO DE ESTUDIANTES
    // =========================
    val estudianteDao
        get() = database.estudianteDao()


    // =========================
    // REPOSITORY DE LIBROS
    // =========================
    val libroRepository: LibroRepository by lazy {
        LibroRepository(libroDao)
    }


    // =========================
    // REPOSITORY DE ESTUDIANTES
    // =========================
    val estudianteRepository: EstudianteRepository by lazy {
        EstudianteRepository(estudianteDao)
    }
}
