package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.model.Libro
import com.example.bibliotech.ui.componentes.BotonMenu


@Composable
fun PantalaDetalleLibro(libro: Libro, onRegresar:() -> Unit) {

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {

        Icon(
            imageVector = Icons.AutoMirrored.Filled.MenuBook,
            contentDescription = "Libro",
            modifier = Modifier.height(30.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = libro.titulo,
        fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Autor: ${libro.autor}")
        Text(text = "Categoría: ${libro.categoria}")
        Text(text = "Año: ${libro.anio}")
        Text(text = "Disponible: ${libro.disponible}")
        Text(text = "Descripción: ${libro.descripcion}")
        Spacer(modifier = Modifier.height(16.dp))

        BotonMenu(texto = "Regresar", onClick = onRegresar)
    }

}