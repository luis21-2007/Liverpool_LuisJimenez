package mx.edu.utez.liverpool_felipe.models

import android.media.Rating
import org.w3c.dom.Comment
import java.util.UUID

data class Comentario (
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val comment: String,
    val rating: Float = 5.0f,
    val author: String? = null

)
