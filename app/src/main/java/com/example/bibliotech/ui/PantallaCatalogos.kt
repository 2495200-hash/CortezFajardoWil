package com.example.bibliotech.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.ui.componentes.TarjetaLibro
import com.example.bibliotech.viewmodel.LibroViewModel
import androidx.compose.foundation.lazy.items
import com.example.bibliotech.ui.componentes.TarjetaLibro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogo(
    onRegresar: () -> Unit,
    onVerDetalles: (Int) -> Unit,
    onAgregarLibro: () -> Unit,
    mensaje: String?,
    onMensajeMostrado: () -> Unit
) {
    val app = LocalContext.current.applicationContext as BibliotecaApplication
    val viewModel: LibroViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LibroViewModel(app as Application) as T
            }
        }
    )
    //Se encarga que la interfaz este pendiente del estado de las lista nueva
    val libros by viewModel.libros.collectAsState()

    //mensaje a mostrar cuando se elimine el libro
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) { // Carga los libros cuando entramos a pantallaCatalogo
        viewModel.cargarLibros()
    }
    LaunchedEffect(mensaje) {
        if (mensaje != null) {
            snackbarHostState.showSnackbar(mensaje)
            onMensajeMostrado()
        }
    }
    //Capturara el texto escrito por el usuario
    var textoBusqueda by remember { mutableStateOf(" ") }
    //lista de ctaegorias
    val categoria = listOf("Todas", "Literatura", "Novela", "Programacion")
    //GUardar una por defecto y/o la que seleccione
    var categoriaSeleccionada by remember { mutableStateOf("Todas") }


//
    //Crear una nueva lista que compare los libros que coincidan con la busqueda
    val librosFiltrados = libros.filter { libro ->
        val coincideTexto = libro.titulo.contains(
            textoBusqueda,
            ignoreCase = true
        ) || libro.autor.contains(
            textoBusqueda,
            ignoreCase = true
        )
        val coincideCategoria = categoriaSeleccionada == "Todas" ||
                libro.categoria == categoriaSeleccionada
        coincideTexto && coincideCategoria
    }



    Scaffold(
        //
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        //añadiremos el boton que enlazara a la oantalla de crear un nuevo libro
        floatingActionButtonPosition = FabPosition.Start, //alineandno a la izquierda

        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarLibro, containerColor = Color.DarkGray) {
                Text("+", color = Color.White)
            }
        },

        topBar = {
            TopAppBar(title = { Text("Catálogo de Libros") })
        })
    { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .padding(5.dp)
                .fillMaxSize()
        ) {
            // ----------- añadido -----------------
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                label = { Text("Buscar libro o autor") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Red)
            )
            //Creacion de los chips de categoria
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.horizontalScroll(state = rememberScrollState())) {
                categoria.forEach { categoria ->
                    FilterChip(
                        selected = categoriaSeleccionada == categoria,
                        onClick = { categoriaSeleccionada = categoria },
                        label = {
                            Text(categoria)
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }




            Spacer(modifier = Modifier.height(16.dp))
            if (librosFiltrados.isEmpty()) {
                //MODIFICACION
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = "Sin resultados",
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No se encontraron libros",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Prueba con otro título o autor")
                }
            } else {

                //--
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(librosFiltrados) { libro ->
                        TarjetaLibro(
                            libro = libro,
                            onVerDetalles = { onVerDetalles(libro.id) })
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}