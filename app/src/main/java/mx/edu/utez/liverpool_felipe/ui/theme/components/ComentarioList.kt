package mx.edu.utez.liverpool_felipe.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.liverpool_felipe.models.Comentario

@Composable
fun ComentarioList(
    comentarios: List<Comentario>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = comentarios,
            key = { comentario -> comentario.id }
        ) { comentario ->
            ComentarioCard(
                comentario = comentario,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComentarioListPreview() {
    val listaComentariosDemo = listOf(
        Comentario(
            title = "Excelente calidad de audio",
            comment = "Los audífonos tienen una cancelación de ruido increíble y la batería dura bastante. Muy recomendado.",
            rating = 5.0f,
            author = "Felipe Jimenez"
        ),
        Comentario(
            title = "Cumple su función",
            comment = "El producto llegó rápido pero la caja venía un poco maltratada.",
            rating = 3.0f,
            author = null
        ),
        Comentario(
            title = "Buena relación calidad-precio",
            comment = "Me gustaron mucho los materiales, se ven bastante resistentes al uso diario.",
            rating = 4.0f,
            author = "María Quintero"
        )
    )

    ComentarioList(comentarios = listaComentariosDemo)
}