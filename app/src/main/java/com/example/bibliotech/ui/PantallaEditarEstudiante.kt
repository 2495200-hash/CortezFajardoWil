package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import com.example.bibliotech.model.Estudiante
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarEstudiante(
    estudiante: Estudiante,
    onGuardar: (Estudiante) -> Unit,
    onCancelar: () -> Unit,

    ) {

    //VARIALES DEL FORMULARIO

    var nombre by remember { mutableStateOf("") }
    var carnet by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }

    //GRADO

    val grados = listOf(
        "1° Bachillerato",
        "2° Bachillerato",
        "3° Bachillerato"
    )

    //SECCIONES
    val secciones = listOf("A","B","C")
    var seccion by remember { mutableStateOf(secciones[0]) }
    var grado by remember { mutableStateOf(grados[0]) }
    var expandirSeccion by remember { mutableStateOf(false) }
    var expandirGrado by remember { mutableStateOf(false) }

    //ESTADO

    var activo by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text( "Editar Estudiante" , color = Color.White)
        },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black)
            )
        }){ padding ->
        Column(modifier = Modifier.padding(padding) { }
    }


}