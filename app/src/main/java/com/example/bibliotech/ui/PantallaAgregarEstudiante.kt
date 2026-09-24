package com.example.bibliotech.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bibliotech.viewmodel.EstudianteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarEstudiante(
    viewModel: EstudianteViewModel,
    onGuardar: () -> Unit,
    onCancelar: () -> Unit
){

    //VARIALES DEL FORMULARIO

    val nombre by remember { mutableStateOf("") }
    val carnet by remember { mutableStateOf("") }
    val apellidos by remember { mutableStateOf("") }

    //GRADO

    val grados = listOf(
        "1° Bachillerato",
        "2° Bachillerato",
        "3° Bachillerato"
    )

    //SECCIONES
    val secciones = listOf("A","B","C")
    var grado by remember { mutableStateOf(grados[0]) }
    var expandirSeccion by remember { mutableStateOf(false) }

    //ESTADO

    var activo by remember { mutableStateOf(false) }

    // INTERFAZ

    Scaffold(
        containerColor = Color. Black,
        topBar = {
            TopAppBar(
                title = {
                    Text( "Agregar Estudiante" , color = Color.White)

            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
    ) {  paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

        }
    }



}