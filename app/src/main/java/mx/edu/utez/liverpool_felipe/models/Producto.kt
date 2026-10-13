package mx.edu.utez.liverpool_felipe.models

import java.util.UUID

data class Producto(
    val id: String = UUID.randomUUID().toString(),
    val brand: String,
    val name: String,
    val price: Double,
    val rating: Float,
    val reviewCount: Int,
    val isFreeShipping: Boolean = true,
    val description: String,
    val image: Int?,
    val reviews: List<Comentario> = emptyList(),
    val isFavorite: Boolean = false
)