package com.example.bibliotech


// Repositories
import android.app.Application
import com.example.bibliotech.data.BibliotecaDatabase
import com.example.bibliotech.data.DatabaseProvider
import com.example.bibliotech.data.EstudianteRepository
import com.example.bibliotech.data.LibroRepository
import com.example.bibliotech.data.PrestamoRepository


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


    val prestamoDao
        get() = database.prestamoDao()


    // =========================
    // REPOSITORY DE PRESTAMOS
    // =========================
    val prestamoRepository: PrestamoRepository by lazy {
        PrestamoRepository(prestamoDao)
    }

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
