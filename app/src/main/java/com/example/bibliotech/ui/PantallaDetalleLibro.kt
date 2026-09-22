package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bibliotech.model.Libro

@Composable
fun PantallaDetalleLibro(
    libro: Libro,
    onRegresar: () -> Unit,
    //añadiremos los parametros para los eventos de los botones editar y eliminar
    onEditar:(Int) -> Unit,
    onEliminar:(Libro) -> Unit,

    // Modifique aqui la linea de abajo para que la ventana emergente aparesca en la pantalla detalle libro
    navController: NavController,


    ) {

    val snackbarHostState = remember { SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val mensaje =
        backStackEntry
            ?.savedStateHandle
            ?.get<String>("mensaje")

        LaunchedEffect(mensaje) {
            if (mensaje != null) {
            snackbarHostState.showSnackbar(mensaje)
                backStackEntry
                ?.savedStateHandle
                ?.remove<String>("mensaje")
        }
    }

    var mostrarDialogo by remember { mutableStateOf(false) }

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold(containerColor = Color.Black,

        // Añadiremos el snackbarHost
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},

        topBar = {
            TopAppBar(
                title = {
                    Text("Detalles del Libro",
                    color = Color.White
                    )
                },
                    colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                )
            )
        }) { paddingValues ->

    Column(modifier = Modifier.fillMaxSize().padding(20.dp).padding(paddingValues)) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.MenuBook,
            contentDescription = "Libro",
            modifier = Modifier.height(30.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = libro.titulo,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Autor: ${libro.autor}", color = Color.White)
        Text("Categoria: ${libro.categoria}", color = Color.White)
        Text("Año: ${libro.anio}", color = Color.White)
        Text("Descripcion: ${libro.descripcion}", color = Color.White)
        Text("Disponible: ${libro.disponible}", color = Color.White)


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        onEditar(libro.id)
                    },
                    modifier = Modifier.weight(1f))
                {
                    Text("Editar")
                }
                Button(
                    onClick = {
                       // onEliminar(libro.id)
                        mostrarDialogo = true
                    },
                    modifier = Modifier.weight(1f))
                {
                    Text("Eliminar")
                }
            }
        


            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onRegresar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Regresar")
            }


            //construimos la ventana emergentre de dialogo
            if (mostrarDialogo) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogo = false },
                    title = {
                        Text("Eliminar Libro")
                            },
                    text = {
                        Text("¿Estás seguro de que deseas eliminar \"${libro.titulo}\"?")
                           },
                    confirmButton = {
                        Button(
                            onClick = {
                                onEliminar(libro)
                                mostrarDialogo = false
                            }
                        ) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { mostrarDialogo = false }
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }
    }
    }

}