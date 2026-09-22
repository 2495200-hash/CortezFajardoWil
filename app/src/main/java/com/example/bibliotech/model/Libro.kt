package com.example.bibliotech.model


// ---------------- IMPORTACIONES ----------------

// Indica que esta clase será una tabla de Room
import androidx.room.Entity

// Permite definir la llave primaria de la tabla
import androidx.room.PrimaryKey

// ----------------------------------------------------
// ENTIDAD LIBRO
// Cada objeto Libro será una fila dentro de la tabla
// llamada "libros".
// ----------------------------------------------------

@Entity(tableName = "libros")
data class Libro(

    // Llave primaria.
    // autoGenerate permite que SQLite genere el ID automáticamente.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Título del libro
    val titulo: String,

    // Nombre del autor
    val autor: String,

    // Género o categoría
    val categoria: String,

    // Año de publicación
    val anio: Int,

    // Indica si el libro está disponible para préstamo
    val disponible: Boolean,
    val descripcion: String
)