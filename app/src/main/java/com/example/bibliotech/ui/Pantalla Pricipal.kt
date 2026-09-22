package com.example.bibliotech.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.ui.componentes.BotonMenu

@Composable
fun PantallaPrincipal(
    onCatalogo: () -> Unit,
    onPrestamo: () -> Unit,
    onPrestados: () -> Unit,
    onEstudiante: () -> Unit
) {
    Log.e("ROOM_TEST", "--- PANTALLA PRINCIPAL VISIBLE ---")

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "BiblioTech",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Sistema de Biblioteca Escolar",
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Consulta libros y administra los préstamos de la biblioteca escolar."
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        BotonMenu(
            texto = "Catálogo de libros",
            onClick = onCatalogo
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        BotonMenu(
            texto = "Registrar préstamo",
            onClick = onPrestamo
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        BotonMenu(
            texto = "Libros prestados",
            onClick = onPrestados
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        BotonMenu(
            texto = "Estudiantes",
            onClick = onEstudiante
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )
    }
}
