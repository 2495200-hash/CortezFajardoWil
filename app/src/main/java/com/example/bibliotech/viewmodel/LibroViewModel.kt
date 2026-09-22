package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// LibroViewModel sera el encagado de solicitar los datos ala repository
class LibroViewModel(application: Application): AndroidViewModel(application) {

    private val repository =
        (application as BibliotecaApplication).libroRepository

    // creamos un estado que almacene la lista de libros, inicialmente vacia
    private val _libros = MutableStateFlow<List<Libro>>(emptyList())
    val libros: StateFlow<List<Libro>> = _libros.asStateFlow()

    //obtenemos el libro seleccionado
    private val _libroSeleccionado = MutableStateFlow<Libro?>(null)
    val LibroSeleccionado : StateFlow<Libro?> = _libroSeleccionado.asStateFlow()

    //METODO QUE LLENA EL VIEW MODEL CONSULTANDO AL ROOM Y ACTUALIZA EL ESTADO
    // OSEA LLENA LA LISTA "_Libros"
    fun cargarLibros() {
        viewModelScope.launch (Dispatchers.IO){
            _libros.value = repository.obtenerLibros()
        }
    }

    fun insertarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertarLibro(libro)
        }
    }

    fun cargarLibroPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _libroSeleccionado.value = repository.obtenerLibroPorId(id)
        }
    }

    //Metodo para actualizar un libro

    fun actualizarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizarLibro(libro)
            _libros.value = repository.obtenerLibros()
            _libroSeleccionado.value = repository.obtenerLibroPorId(libro.id)
        }
    }

    //METODO PARA ELIMINAR UN LIBRO
    fun eliminarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.eliminarLibro(libro)
            _libros.value = repository.obtenerLibros()
        }
    }
}