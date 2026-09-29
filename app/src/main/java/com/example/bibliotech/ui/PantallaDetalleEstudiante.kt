package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bibliotech.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleEstudiante(
    estudiante : Estudiante,
    onRegresar: () -> Unit,
    navController: NavHostController,
    onEditar: (Int) -> Unit,
    onEliminar: (Estudiante) -> Unit
) {
    var mostrarDialogo by remember { mutableStateOf(false) }
    val mensaje = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getMutableStateFlow<String?>("mensaje", initialValue = null)
        ?.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(mensaje?.value) {
        mensaje?.value?.let {
            snackbarHostState.showSnackbar(it)
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.set("mensaje", null)
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = { Text("Detalle Estudiante", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding().fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Estudiante",
                tint = Color.White,
                modifier = Modifier.size(15.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${estudiante.nombres} ${estudiante.apellidos}",
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Carnet: ${estudiante.carnet}", color = Color.White)
            Text(text = "Grado: ${estudiante.grado}", color = Color.White)
            Text(text = "Seccion: ${estudiante.seccion}", color = Color.White)
            Text(
                text = if (estudiante.activo) "Estado: Activo"
                else "Estado: Inactivo",
                color = if (estudiante.activo) Color.Green
                else Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onEditar(estudiante.id) }, modifier = Modifier.fillMaxWidth())
            {
                Text("Editar")
            }

            Button(
                onClick = { mostrarDialogo = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            )
            {
                Text("Eliminar")
            }
            OutlinedButton(
                onClick = { onRegresar() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Regresar")
            }

            if (mostrarDialogo) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogo = false },
                    title = { Text("Eliminar Estudiante") },
                    text = { Text("¿Estás seguro de que deseas eliminar este estudiante?") },
                    confirmButton = {
                        TextButton(onClick = {
                            mostrarDialogo = false
                            onEliminar(estudiante)}
                        ) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            mostrarDialogo = false
                        }) {
                            Text("Cancelar")
                        }
                    }
                )
               }
            }
        }
    }