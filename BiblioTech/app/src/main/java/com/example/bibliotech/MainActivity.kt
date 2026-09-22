package com.example.bibliotech
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.bibliotech.ui.Navegacion
import com.example.bibliotech.ui.theme.BiblioTechTheme


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.bibliotech.data.librosPrueba
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val TAG = "ROOM_TEST"
        Log.e(TAG, "!!! ESTA ES LA OTRA MAIN ACTIVITY !!!")

        try {
            val app = application as BibliotecaApplication
            val repository = app.libroRepository

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val libros = repository.obtenerLibros()
                    if (libros.isEmpty()) {
                        librosPrueba.forEach { repository.insertarLibro(it) }
                    }
                    val listaFinal = repository.obtenerLibros()
                    Log.e(TAG, "CANTIDAD DE LIBROS: ${listaFinal.size}")
                    
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "App activa - BD: ${listaFinal.size}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error en corrutina: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error en inicialización: ${e.message}")
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }

        setContent {
            BiblioTechTheme {
                val navController = rememberNavController()
                Navegacion(navController = navController)
            }
        }
    }
}
