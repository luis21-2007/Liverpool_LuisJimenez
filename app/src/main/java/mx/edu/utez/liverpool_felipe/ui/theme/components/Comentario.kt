package mx.edu.utez.liverpool_felipe.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.liverpool_felipe.models.Comentario

@Composable
fun ComentarioCard(
    comentario: Comentario,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = comentario.author ?: "Usuario anónimo",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )


                Row(verticalAlignment = Alignment.CenterVertically) {
                    val fullStars = comentario.rating.toInt()
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Estrella",
                            tint = if (index < fullStars) Color(0xFFFFB300) else Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${comentario.rating}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = comentario.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = comentario.comment,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ComentarioCardPreview() {
    val comentarioConAutor = Comentario(
        title = "Excelente calidad de audio",
        comment = "Los audífonos tienen una cancelación de ruido increíble y la batería dura bastante. Muy recomendado.",
        rating = 5.0f,
        author = "Felipe Jimenez"
    )

    val comentarioAnonimo = Comentario(
        title = "Cumple su función",
        comment = "El producto llegó rápido pero la caja venía un poco maltratada.",
        rating = 3.0f,
        author = null
    )

    Column(modifier = Modifier.padding(16.dp)) {
        ComentarioCard(comentario = comentarioConAutor)
        Spacer(modifier = Modifier.height(8.dp))
        ComentarioCard(comentario = comentarioAnonimo)
    }
}