package com.example.bibliotech.ui

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
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bibliotech.data.librosPrueba
import com.example.bibliotech.ui.componentes.TarjetaLibro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogo(
    onRegresar: () -> Unit,
    onVerDetalles: (Int) -> Unit
) {

    //captura el texto escrito por el usuario
    var textoBusqueda by remember { mutableStateOf(" ") }

    //lista categorias
    val categoria = listOf("Todas", "Literatura", "Novela", "programacion")
    //guardar una por defecto y/o la que seleccione
    var categoriaSeleccionada by remember { mutableStateOf("Todas") }

    // crear una nueva lista que compare que los libros cincidan con la busqueda
    val librosFiltrados = librosPrueba.filter { libro ->
        val coincideTexto = libro.titulo.contains(textoBusqueda,
            true) || libro.autor.contains(textoBusqueda,
                true)
        val coincideCategoria = categoriaSeleccionada == "Todas" ||
                libro.categoria == categoriaSeleccionada
        coincideTexto && coincideCategoria
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Catálogo de Libros") })
    }){ padding ->
        Column(
            modifier = Modifier.padding(padding)
                .padding(5.dp)
                .fillMaxSize()
        ){
            //--------------añadido--------------------
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it }, label = { Text("Buscar libro o autor") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(Color.Red)
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
                Column(modifier = Modifier.fillMaxWidth()
                    .padding(top=40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = "Sin resultados",
                    modifier = Modifier.size(48.dp))

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "No se encontraron Libros",
                    fontWeight = FontWeight.Bold
                    )
                    Text(text = "Prueba con otro titulo o autor")
                }

            } else {

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

        }
    }


}
