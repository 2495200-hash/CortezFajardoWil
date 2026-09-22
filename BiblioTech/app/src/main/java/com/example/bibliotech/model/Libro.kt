package com.example.bibliotech.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libros")
data class Libro(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val titulo:String,
    val autor:String,
    val categoria:String,
    val anio:Int,
    val disponible:Boolean,
    val descripcion: String
)