package com.example.bibliotech.data

// ---------------- IMPORTACIONES ----------------


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibliotech.model.Estudiante


// Importamos la entidad Libro
import com.example.bibliotech.model.Libro


// ----------------------------------------------------
// BASE DE DATOS PRINCIPAL
// Esta clase representa la base de datos SQLite
// utilizada por toda la aplicación.
// ----------------------------------------------------


@Database(


    // Lista de tablas que tendrá la base de datos
    entities = [Libro::class, Estudiante::class],






    // Primera versión de la base de datos
    //version = 1,
    version = 2,


    // No exportaremos el esquema durante el curso
    exportSchema = false
)
abstract class BibliotecaDatabase : RoomDatabase() {


    // El DAO se agregará en la siguiente guía.
    abstract fun libroDao(): LibroDao


    // =========================
    // DAO DE ESTUDIANTES
    // =========================
    abstract fun estudianteDao(): EstudianteDao


}
