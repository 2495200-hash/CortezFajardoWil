package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Estudiante
import com.example.bibliotech.model.Libro
import com.example.bibliotech.model.Prestamo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PrestamoViewModel(application: Application)
    : AndroidViewModel(application) {

    // TRAEMOS TODOS LOS REPOSITORIOS
    private val prestamoRepository =
        (application as BibliotecaApplication).prestamoRepository

    private val libroRepository =
        (application as BibliotecaApplication).libroRepository

    private val estudianteRepository =
        (application as BibliotecaApplication).estudianteRepository

    //LBROS DISPONIBLES
    private val _librosDisponibles =
        MutableStateFlow<List<Libro>>(emptyList())

    val librosDisponibles = _librosDisponibles

    // ETUDIANTES ACTIVOS
    private val _estudiantesActivos =
        MutableStateFlow<List<Estudiante>>(emptyList())

    val estudiantesActivos = _estudiantesActivos

    //PRESTAMOS ACTIVOS

    private val _prestamosActivos =
        MutableStateFlow<List<Prestamo>>(emptyList())

    val prestamosActivos = _prestamosActivos

    // SBER SI EL PRESTAMO YA FUE GUARDADO O NO

    private val _prestamoGuardado =
        MutableStateFlow<Boolean>(false)

    val prestamoGuardado = _prestamoGuardado

    //TRAER LOS DATOS AL MOMETO DE HACER EL REGISTRO DEL PRESTAMO

    fun cargarDatos(){
        viewModelScope.launch(Dispatchers.IO) {
            //obtener todoslos libros disponibles
            val libros = libroRepository.obtenerLibros()

            // dejamos unicamente los libros disponibles
            _librosDisponibles.value = libros.filter { it.disponible }

            //obtener todos los estudiantes activos
            val estudiantes = estudianteRepository.obtenerEstudiantes()

            // dejamos unicamente los estudiantes activos
            _estudiantesActivos.value = estudiantes.filter { it.activo }

            //obtener todos los prestamos activos
            _prestamosActivos.value = prestamoRepository.obtenerPrestamosActivos()
        }


    }
    // GUARDAR EL PRESTAMO
    fun guardarPrestamo(libroId: Int, estudianteId: Int){
        viewModelScope.launch(Dispatchers.IO) {

            // Buscar el libro que se a selecciondo
            val libro = libroRepository.obtenerLibroPorId(libroId)

            //VERIFICAMOS QUE ESE LIBRO EXISTA Y QUE ESTE DISPONIBLE
            if (libro == null || !libro.disponible) {
                return@launch
            }

            //OBTENER LA FCHA ACTUAL EN EL INSTANTE DE GUARDAR EL PRESTAMO
            val fechaActual = SimpleDateFormat(
            "dd/MM/yyyy", Locale.getDefault()).format(Date())

            // creamos el prestamo
            val nuevosPresamos = Prestamo(
                idLibro = libroId,
                idEstudiante = estudianteId,
                fechaPrestamo = fechaActual,
                fechaDevolucion = "",
                devuelto = false
            )

            // guardamos en la base de datos
            prestamoRepository.insertarPrestamo(nuevosPresamos)

            // ponerellibro en disponible como falso porque se acaba de prestar
            val libroActualizado = libro.copy(disponible = false)
            libroRepository.actualizarLibro(libroActualizado)

            // Actualizar ls datos de las listas
            val librosActualizados = libroRepository.obtenerLibros()
            _librosDisponibles.value = librosActualizados.filter { it.disponible }
            _prestamosActivos.value = prestamoRepository.obtenerPrestamosActivos()
            _prestamoGuardado.value = true


        }
    }

    // Reiniciar el estado del proceso

    fun reiniciarEstadoGuardado(){
        _prestamoGuardado.value = false
    }

}