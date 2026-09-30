package com.example.bibliotech.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "prestamos",
     foreignKeys = [
           ForeignKey(
               entity = Libro::class,
               parentColumns = ["id"],
               childColumns = ["idLibro"],
               //onDelete = ForeignKey.CASCADE
           ),
           ForeignKey(
               entity = Estudiante::class,
               parentColumns = ["id"],
               childColumns = ["idEstudiante"],
               //onDelete = ForeignKey.CASCADE
           )
     ]
)


data class Prestamo (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // Libro prestado
    val idLibro: Int,
    // Estudiante que lo presta
    val idEstudiante: Int,
    // Fecha de prestamo
    val fechaPrestamo: String,
    // Fecha de devolucion
    val fechaDevolucion: String,
    // Estado del prestamo
    val devuelto: Boolean
){
}