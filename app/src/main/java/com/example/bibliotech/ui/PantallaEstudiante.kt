package com.example.bibliotech.ui

// ===============================
// IMPORTACIONES DE COMPOSE
// ===============================


// ===============================
// IMPORTACIONES DE MATERIAL 3
// ===============================


// ===============================
// IMPORTACIONES DE RUNTIME
// ===============================


// ===============================
// IMPORTACIONES DE VIEWMODEL
// ===============================


// ===============================
// OTRAS IMPORTACIONES
// ===============================


// ===============================
// IMPORTACIONES DEL PROYECTO
// ===============================
import android.app.Application
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.ui.componentes.TarjetaEstudiante
import com.example.bibliotech.viewmodel.EstudianteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEstudiantes(
    onRegresar: () -> Unit,
    onVerDetalles: (Int) -> Unit,
    onAgregarEstudiante: () -> Unit,
    mensaje: String?,
    onMensajeMostrado: () -> Unit
) {


    // =========================================================
    // OBTENEMOS LA APLICACIÓN
    // =========================================================
    // Nos permite acceder al Repository de estudiantes
    // mediante BibliotecaApplication.
    val app =
        LocalContext.current.applicationContext as BibliotecaApplication




    // =========================================================
    // CREAMOS EL VIEWMODEL
    // =========================================================
    val viewModel: EstudianteViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {


            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {


                return EstudianteViewModel(
                    app as Application
                ) as T
            }
        }
    )




    // =========================================================
    // OBSERVAMOS LA LISTA DE ESTUDIANTES
    // =========================================================
    // collectAsState permite que la interfaz se actualice
    // automáticamente cuando cambia la lista.
    val estudiantes by viewModel.estudiantes.collectAsState()




    // =========================================================
    // CONFIGURACIÓN DEL SNACKBAR
    // =========================================================
    val snackbarHostState =
        remember { SnackbarHostState() }




    // =========================================================
    // CARGAR ESTUDIANTES
    // =========================================================
    // Se ejecuta cuando entramos a esta pantalla.
    LaunchedEffect(Unit) {
        viewModel.cargarEstudiantes()
    }




    // =========================================================
    // MOSTRAR MENSAJES
    // =========================================================
    // Por ejemplo:
    // "✓ Estudiante agregado correctamente"
    // "✓ Estudiante eliminado correctamente"
    LaunchedEffect(mensaje) {


        if (mensaje != null) {


            snackbarHostState.showSnackbar(mensaje)


            onMensajeMostrado()
        }
    }




    // =========================================================
    // CAMPO DE BÚSQUEDA
    // =========================================================
    var textoBusqueda by remember {
        mutableStateOf("")
    }




    // =========================================================
    // LISTA DE GRADOS
    // =========================================================
    val grados = listOf(
        "Todos",
        "1° Bachillerato",
        "2° Bachillerato",
        "3° Bachillerato"
    )




    // =========================================================
    // GRADO SELECCIONADO
    // =========================================================
    var gradoSeleccionado by remember {
        mutableStateOf("Todos")
    }




    // =========================================================
    // LISTA DE SECCIONES
    // =========================================================
    val secciones = listOf(
        "Todas",
        "A",
        "B",
        "C"
    )




    // =========================================================
    // SECCIÓN SELECCIONADA
    // =========================================================
    var seccionSeleccionada by remember {
        mutableStateOf("Todas")
    }




    // =========================================================
    // FILTRAR ESTUDIANTES
    // =========================================================
    val estudiantesFiltrados = estudiantes.filter { estudiante ->


        // -----------------------------------------------------
        // BUSCAR POR CARNET, NOMBRES O APELLIDOS
        // -----------------------------------------------------
        val coincideTexto =
            estudiante.carnet.contains(
                textoBusqueda,
                ignoreCase = true
            ) ||
                    estudiante.nombres.contains(
                        textoBusqueda,
                        ignoreCase = true
                    ) ||
                    estudiante.apellidos.contains(
                        textoBusqueda,
                        ignoreCase = true
                    )




        // -----------------------------------------------------
        // FILTRAR POR GRADO
        // -----------------------------------------------------
        val coincideGrado =
            gradoSeleccionado == "Todos" ||
                    estudiante.grado == gradoSeleccionado




        // -----------------------------------------------------
        // FILTRAR POR SECCIÓN
        // -----------------------------------------------------
        val coincideSeccion =
            seccionSeleccionada == "Todas" ||
                    estudiante.seccion == seccionSeleccionada




        // -----------------------------------------------------
        // EL ESTUDIANTE DEBE CUMPLIR LOS TRES FILTROS
        // -----------------------------------------------------
        coincideTexto &&
                coincideGrado &&
                coincideSeccion
    }




    // =========================================================
    // ESTRUCTURA PRINCIPAL
    // =========================================================
    Scaffold(


        // -----------------------------------------------------
        // SNACKBAR
        // -----------------------------------------------------
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },




        // -----------------------------------------------------
        // BOTÓN FLOTANTE
        // -----------------------------------------------------
        floatingActionButtonPosition = FabPosition.Start,


        floatingActionButton = {


            FloatingActionButton(
                onClick = onAgregarEstudiante,
                containerColor = Color.DarkGray
            ) {


                Text(
                    text = "+",
                    color = Color.White
                )
            }
        },




        // -----------------------------------------------------
        // BARRA SUPERIOR
        // -----------------------------------------------------
        topBar = {


            TopAppBar(
                title = {
                    Text("Estudiantes")
                }
            )
        }


    ) { padding ->




        // =====================================================
        // CONTENIDO
        // =====================================================
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(5.dp)
                .fillMaxSize()
        ) {




            // =================================================
            // BUSCADOR
            // =================================================
            OutlinedTextField(


                value = textoBusqueda,


                onValueChange = {
                    textoBusqueda = it
                },


                label = {
                    Text("Buscar estudiante")
                },


                placeholder = {
                    Text("Carnet, nombres o apellidos")
                },


                modifier = Modifier.fillMaxWidth()
            )




            // =================================================
            // FILTRO DE GRADO
            // =================================================
            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Text(
                text = "Grado",
                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )




            Row(
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            ) {


                grados.forEach { grado ->


                    FilterChip(


                        selected =
                            gradoSeleccionado == grado,


                        onClick = {
                            gradoSeleccionado = grado
                        },


                        label = {
                            Text(grado)
                        }
                    )


                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                }
            }




            // =================================================
            // FILTRO DE SECCIÓN
            // =================================================
            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Text(
                text = "Sección",
                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )




            Row(
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            ) {


                secciones.forEach { seccion ->


                    FilterChip(


                        selected =
                            seccionSeleccionada == seccion,


                        onClick = {
                            seccionSeleccionada = seccion
                        },


                        label = {
                            Text(seccion)
                        }
                    )


                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                }
            }




            // =================================================
            // ESPACIO ANTES DE LA LISTA
            // =================================================
            Spacer(
                modifier = Modifier.height(16.dp)
            )




            // =================================================
            // SI NO HAY RESULTADOS
            // =================================================
            if (estudiantesFiltrados.isEmpty()) {


                Column(


                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),


                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {


                    Icon(


                        imageVector = Icons.Default.Person,


                        contentDescription =
                            "Sin resultados",


                        modifier = Modifier.size(48.dp)
                    )




                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )




                    Text(
                        text = "No se encontraron estudiantes",
                        fontWeight = FontWeight.Bold
                    )




                    Text(
                        text =
                            "Prueba con otro carnet, nombre o filtro"
                    )
                }


            } else {




                // =================================================
                // LISTA DE ESTUDIANTES
                // =================================================
                LazyColumn(


                    verticalArrangement =
                        Arrangement.spacedBy(10.dp),


                    modifier = Modifier.weight(1f)
                ) {


                    items(
                        estudiantesFiltrados
                    ) { estudiante ->


                        TarjetaEstudiante(


                            estudiante = estudiante,


                            onVerDetalles = {
                                onVerDetalles(
                                    estudiante.id
                                )
                            }
                        )
                    }
                }
            }




            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

    }
}
