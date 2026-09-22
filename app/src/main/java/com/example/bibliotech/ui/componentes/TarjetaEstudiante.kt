package com.example.bibliotech.ui.componentes


// ============================================
// IMPORTACIONES DE LAYOUT
// ============================================
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width


// ============================================
// IMPORTACIONES DE MATERIAL 3
// ============================================
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text


// ============================================
// IMPORTACIONES DE COMPOSE
// ============================================
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// ============================================
// MODELO
// ============================================
import com.example.bibliotech.model.Estudiante




// ============================================
// TARJETA DE ESTUDIANTE
// ============================================
// Este componente representa visualmente a un
// estudiante dentro de la lista.
//
// Es equivalente a TarjetaLibro, pero adaptado
// a los datos de la entidad Estudiante.
// ============================================
@Composable
fun TarjetaEstudiante(
    estudiante: Estudiante,
    onVerDetalles: () -> Unit
) {


    Card(
        modifier = Modifier.fillMaxWidth()
    ) {


        Column(
            modifier = Modifier.padding(10.dp)
        ) {


            // ========================================
            // INFORMACIÓN PRINCIPAL
            // ========================================
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                // Icono que representa al estudiante
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Estudiante",
                    modifier = Modifier.size(32.dp)
                )


                Spacer(
                    modifier = Modifier.width(12.dp)
                )


                Column {


                    // Nombre completo
                    Text(
                        text = "${estudiante.nombres} ${estudiante.apellidos}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )


                    // Número de carnet
                    Text(
                        text = "Carnet: ${estudiante.carnet}"
                    )


                    // Grado y sección
                    Text(
                        text = "${estudiante.grado} — Sección ${estudiante.seccion}"
                    )
                }
            }
        }




        // ========================================
        // BOTÓN VER
        // ========================================
        Button(
            onClick = onVerDetalles,
            modifier = Modifier
                .align(Alignment.End)
                .padding(6.dp)
        ) {
            Text("Ver")
        }
    }
}
