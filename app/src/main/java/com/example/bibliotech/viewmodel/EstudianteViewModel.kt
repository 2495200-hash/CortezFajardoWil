package com.example.bibliotech.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Estudiante
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// EstudianteViewModel será el encargado de solicitar los datos al Repository
class EstudianteViewModel(application: Application) : AndroidViewModel(application) {


    private val repository =
        (application as BibliotecaApplication).estudianteRepository


    // =========================
    // LISTA DE ESTUDIANTES
    // =========================
    private val _estudiantes =
        MutableStateFlow<List<Estudiante>>(emptyList())


    val estudiantes: StateFlow<List<Estudiante>> =
        _estudiantes.asStateFlow()


    // =========================
    // ESTUDIANTE SELECCIONADO
    // =========================
    private val _estudianteSeleccionado =
        MutableStateFlow<Estudiante?>(null)


    val estudianteSeleccionado: StateFlow<Estudiante?> =
        _estudianteSeleccionado.asStateFlow()


    // =========================
    // READ
    // Cargar todos los estudiantes
    // =========================
    fun cargarEstudiantes() {
        viewModelScope.launch(Dispatchers.IO) {
            _estudiantes.value = repository.obtenerEstudiantes()
        }
    }


    // =========================
    // CREATE
    // Insertar estudiante
    // =========================
    fun insertarEstudiante(estudiante: Estudiante) {
        viewModelScope.launch(Dispatchers.IO) {


            repository.insertarEstudiante(estudiante)


            _estudiantes.value =
                repository.obtenerEstudiantes()
        }
    }


    // =========================
    // READ
    // Obtener un estudiante por ID
    // =========================
    fun cargarEstudiantePorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {


            _estudianteSeleccionado.value =
                repository.obtenerEstudiantePorId(id)
        }
    }


    // =========================
    // UPDATE
    // Actualizar estudiante
    // =========================
    fun actualizarEstudiante(estudiante: Estudiante) {


        viewModelScope.launch(Dispatchers.IO) {


            repository.actualizarEstudiante(estudiante)


            _estudiantes.value =
                repository.obtenerEstudiantes()


            _estudianteSeleccionado.value =
                repository.obtenerEstudiantePorId(estudiante.id)
        }
    }


    // =========================
    // DELETE
    // Eliminar estudiante
    // =========================
    fun eliminarEstudiante(estudiante: Estudiante) {


        viewModelScope.launch(Dispatchers.IO) {


            repository.eliminarEstudiante(estudiante)


            _estudiantes.value =
                repository.obtenerEstudiantes()
        }
    }


}
