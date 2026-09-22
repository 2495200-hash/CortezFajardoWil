package com.example.bibliotech.ui
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bibliotech.data.librosPrueba

@Composable
fun Navegacion(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {

            PantallaPrincipal(
                onCatalogo = {
                    navController.navigate("catalogo")
                },
                onPrestamo = {
                    navController.navigate("prestamo")
                },
                onPrestados = {
                    navController.navigate("prestados")
                }
            )
        }

        composable("catalogo") {

            PantallaCatalogo(
                onRegresar = {
                    navController.popBackStack()
                },
                onVerDetalles = {idLibro ->
                    navController.navigate("detalle/$idLibro")}
            )
        }

        composable("detalle/{idLibro}"){
            val idLibro = it.arguments?.getString("idLibro")?.toIntOrNull()
            val libro = librosPrueba.find { it.id == idLibro }

            if (libro != null) {
                PantalaDetalleLibro(libro = libro,
                    onRegresar = { navController.popBackStack()}
                )
            }

        }


        composable("prestamo") {

            PantallaPrestamo(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }

        composable("prestados") {

            PantallaLibrosPrestados(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }
    }
}
