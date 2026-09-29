package com.example.bibliotech.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.bibliotech.model.Estudiante

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
        Column(modifier = Modifier.padding(padding)
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(20.dp))

            {

            OutlinedTextField(
                value = carnet,
                onValueChange = { carnet = it },
                label = { Text("Carnet") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.Yellow,
                    unfocusedLabelColor = Color.White,
                    cursorColor = Color.White
                )

            )
                Spacer(modifier = Modifier.padding(12.dp))






                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.Yellow,
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color.White
                    )

                )
                Spacer(modifier = Modifier.padding(12.dp))

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.Yellow,
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color.White
                    )

                )
                Spacer(modifier = Modifier.padding(12.dp))


                // EXPANDIR GRADO
                ExposedDropdownMenuBox(
                    expanded = expandirGrado,
                    onExpandedChange = { expandirGrado = !expandirGrado }
                ) {
                    OutlinedTextField(
                        value = grado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Grado") },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirGrado) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color.Yellow,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = Color.Yellow,
                            unfocusedLabelColor = Color.White,
                            cursorColor = Color.White
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandirGrado,
                        onDismissRequest = { expandirGrado = false }
                    ) {
                        grados.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    grado = it
                                    expandirGrado = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))

                // SECCION
                ExposedDropdownMenuBox(
                    expanded = expandirSeccion,
                    onExpandedChange = { expandirSeccion = !expandirSeccion }
                ) {
                    OutlinedTextField(
                        value = seccion,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Seccion") },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandirSeccion) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color.Yellow,
                            unfocusedBorderColor = Color.LightGray,
                            focusedLabelColor = Color.Yellow,
                            unfocusedLabelColor = Color.White,
                            cursorColor = Color.White
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandirSeccion,
                        onDismissRequest = { expandirSeccion = false }
                    ) {
                        secciones.forEach {
                            DropdownMenuItem(
                                text = {Text(it) },
                                onClick = {
                                    seccion = it
                                    expandirSeccion = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row{
                    Checkbox(
                        checked = activo,
                        onCheckedChange = { activo = it }
                    )
                    Text(
                        text = "Activo",
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        val estudianteEditado = estudiante.copy(
                            carnet = carnet,
                            nombres = nombre,
                            apellidos = apellidos,
                            grado = grado,
                            seccion = seccion,
                            activo = activo
                        )
                        onGuardar(estudianteEditado)
                    },
                    modifier = Modifier.fillMaxWidth()
                ){Text("Guardar Estudiante")}

                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedButton(
                    onClick = { onCancelar() },
                    modifier = Modifier.fillMaxWidth()
                ){Text("Cancelar")}
        }
    }


}