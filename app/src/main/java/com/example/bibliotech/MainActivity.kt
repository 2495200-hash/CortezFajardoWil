package com.example.bibliotech
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.bibliotech.data.librosPrueba
import com.example.bibliotech.ui.Navegacion
import com.example.bibliotech.ui.theme.BiblioTechTheme
import android.widget.Toast
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val TAG = "ROOM_TEST"
        Log.e(TAG, "!!! LA APLICACION SE HA INICIADO CORRECTAMENTE !!!")

        val app = application as BibliotecaApplication
        val repository = app.libroRepository

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val libros = repository.obtenerLibros()
                if (libros.isEmpty()) {
                    Log.e(TAG, "Base de datos vacía. Insertando datos...")
                    librosPrueba.forEach { repository.insertarLibro(it) }
                }
                
                val listaFinal = repository.obtenerLibros()
                Log.e(TAG, "CANTIDAD DE LIBROS EN BD: ${listaFinal.size}")
                
                listaFinal.forEach { 
                    Log.e(TAG, "-> Libro: ${it.titulo} (ID: ${it.id})")
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Base de datos cargada: ${listaFinal.size} libros", Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
                Log.e(TAG, "ERROR CRITICO: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error en BD: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        setContent {
            BiblioTechTheme {
                val navController = rememberNavController()
                Navegacion(navController = navController)
            }
        }
    }
}
