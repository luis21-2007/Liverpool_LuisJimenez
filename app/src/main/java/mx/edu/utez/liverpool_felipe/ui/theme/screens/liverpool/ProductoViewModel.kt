package mx.edu.utez.liverpool_felipe.ui.theme.screens.liverpool

import androidx.compose.runtime.mutableStateOf
import mx.edu.utez.liverpool_felipe.models.Producto
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import mx.edu.utez.liverpool_felipe.R
import mx.edu.utez.liverpool_felipe.models.Comentario


class ProductoViewModel : ViewModel() {

    private val _productos = mutableStateListOf<Producto>()
    val productos: List<Producto> get() = _productos

    var productoSeleccionado by mutableStateOf<Producto?>(null)
        private set

    init {
        cargarProductosIniciales()
    }

    fun seleccionarProducto(producto: Producto) {
        productoSeleccionado = producto
    }

    fun Favorite(producto: Producto) {
        val index = _productos.indexOfFirst { it.id == producto.id }
        if (index != -1) {
            val itemActual = _productos[index]
            _productos[index] = itemActual.copy(isFavorite = !itemActual.isFavorite)
        }
    }

    private fun cargarProductosIniciales() {
        _productos.addAll(
            listOf(
                Producto(
                    brand = "Sony",
                    name = "Audífonos Inalámbricos Noise Cancelling WH-1000XM5",
                    price = 7499.00,
                    rating = 4.8f,
                    reviewCount = 2,
                    isFreeShipping = true,
                    description = "La tecnología Noise Cancelling con múltiples micrófonos mantiene fuera los sonidos de alta y media frecuencia.",
                    image = R.drawable.sony,
                    isFavorite = true,
                    reviews = listOf(
                        Comentario(
                            title = "Excelente cancelación de ruido",
                            comment = "Los audífonos superaron mis expectativas, la batería dura bastante.",
                            rating = 5.0f,
                            author = "Luis Quintero"
                        ),
                        Comentario(
                            title = "Excelente producto",
                            comment = "La batería dura bastante.",
                            rating = 5.0f,
                            author = "Felipe Jimenez"
                        )
                    )
                ),
                Producto(
                    brand = "LG",
                    name = "Pantalla Smart TV 55\" OLED 4K UHD",
                    price = 18999.00,
                    rating = 4.6f,
                    reviewCount = 0,
                    isFreeShipping = true,
                    description = "Disfruta de colores vibrantes y negros perfectos.",
                    image = R.drawable.pantalla,
                    isFavorite = false
                ),
                Producto(
                    brand = "Xbox Series X",
                    name = "Xbox Series X- 4k ",
                    price = 10000.00,
                    rating = 4.6f,
                    reviewCount = 0,
                    isFreeShipping = true,
                    description = "Disfruta de los mejores videojuegos de Xbox.",
                    image = R.drawable.xbox,
                    isFavorite = false
                ),
                Producto(
                    brand = "Iphone 18-pro",
                    name = "Iphone 18 promax el mejor celular de apple",
                    price = 28999.00,
                    rating = 4.6f,
                    reviewCount = 0,
                    isFreeShipping = true,
                    description = "Disfruta de colores vibrantes y un procesador mejor.",
                    image = R.drawable.apple,
                    isFavorite = false
                )

            )
        )
    }
}
