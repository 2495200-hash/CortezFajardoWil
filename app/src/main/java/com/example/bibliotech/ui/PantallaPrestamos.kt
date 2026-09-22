package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaPrestamo(
    onRegresar: () -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Registrar préstamo",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Aquí se registrarán los préstamos."
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = onRegresar
        ) {
            Text("Regresar")
        }
    }
}
