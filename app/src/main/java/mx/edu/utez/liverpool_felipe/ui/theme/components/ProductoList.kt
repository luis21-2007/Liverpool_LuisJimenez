package mx.edu.utez.liverpool_felipe.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.liverpool_felipe.R
import mx.edu.utez.liverpool_felipe.models.Producto

@Composable
fun ProductoList(
    productos: List<Producto>,
    onFavoriteClick: (Producto) -> Unit = {},
    onProductoClick: (Producto) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = productos,
            key = { producto -> producto.id }
        ) { producto ->
            ProductoCard(
                producto = producto,
                onFavoriteClick = onFavoriteClick,
                onProductoClick = onProductoClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductoListPreview() {
    val listaProductosDemo = listOf(
        Producto(
            brand = "Sony",
            name = "Audífonos Noise Cancelling WH-1000XM5",
            price = 7499.00,
            rating = 4.8f,
            reviewCount = 230,
            isFreeShipping = true,
            description = "Excelente cancelación de ruido",
            image = R.drawable.liverpool,
            isFavorite = true
        ),
        Producto(
            brand = "LG",
            name = "Pantalla Smart TV 55\" OLED 4K",
            price = 18999.00,
            rating = 4.6f,
            reviewCount = 85,
            isFreeShipping = true,
            description = "Calidad de imagen superior",
            image = R.drawable.liverpool,
            isFavorite = false
        ),
        Producto(
            brand = "Apple",
            name = "iPad Air 11\" M2 128GB Space Gray",
            price = 12999.00,
            rating = 4.9f,
            reviewCount = 140,
            isFreeShipping = false,
            description = "Potencia y portabilidad",
            image = R.drawable.liverpool,
            isFavorite = false
        ),
        Producto(
            brand = "Nike",
            name = "Tenis Air Max 270 Hombre",
            price = 3299.00,
            rating = 4.5f,
            reviewCount = 60,
            isFreeShipping = true,
            description = "Comodidad para el día a día",
            image = R.drawable.liverpool,
            isFavorite = true
        )
    )

    ProductoList(productos = listaProductosDemo)
}