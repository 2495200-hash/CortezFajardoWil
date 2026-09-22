package com.example.bibliotech.model


// Convierte la clase en una tabla de Room
import androidx.room.Entity


// Define la llave primaria de la tabla
import androidx.room.PrimaryKey


// La clase representa la tabla "Estudiantes" en SQLite
// y también sirve como modelo para la interfaz gráfica.
@Entity("Estudiantes")
data class Estudiante(


    // Identificador único generado automáticamente
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    // Número de carnet del estudiante
    val carnet: String,


    // Nombres del estudiante
    val nombres: String,


    // Apellidos del estudiante
    val apellidos: String,


    // Grado que cursa
    val grado: String,


    // Sección del grado
    val seccion: String,


    // Estado del estudiante dentro del sistema
    val activo: Boolean
)
