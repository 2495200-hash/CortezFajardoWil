package com.example.bibliotech.data

// ---------------- IMPORTACIONES ----------------


// Importamos la entidad Libro
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bibliotech.model.Estudiante
import com.example.bibliotech.model.Libro
import com.example.bibliotech.model.Prestamo


// ----------------------------------------------------
// BASE DE DATOS PRINCIPAL
// Esta clase representa la base de datos SQLite
// utilizada por toda la aplicación.
// ----------------------------------------------------


@Database(


    // Lista de tablas que tendrá la base de datos
    entities = [Libro::class,
                Estudiante::class,
        // Añadimios otra entidad en la version 3
                Prestamo::class,
             ],




    // Primera versión de la base de datos
    //version = 1,
    //version = 2,
    version = 3,


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

    abstract


}
