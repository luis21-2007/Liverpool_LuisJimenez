package mx.edu.utez.liverpool_felipe.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.liverpool_felipe.models.Producto
import mx.edu.utez.liverpool_felipe.R

@Composable
fun ProductoCard(
    producto: Producto,
    onFavoriteClick: (Producto) -> Unit = {},
    onProductoClick: (Producto) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onProductoClick(producto) },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                if (producto.image != null) {
                    Image(
                        painter = painterResource(id = producto.image),
                        contentDescription = producto.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFFF0F0F0),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "Sin imagen",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }


                IconButton(
                    onClick = { onFavoriteClick(producto) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = if (producto.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (producto.isFavorite) Color.Red else Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = producto.brand.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )


            Text(
                text = producto.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFB300),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${producto.rating} (${producto.reviewCount})",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))


            Text(
                text = "$${String.format("%.2f", producto.price)}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // Etiqueta de Envío Gratis
            if (producto.isFreeShipping) {
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Envío Gratis",
                        color = Color(0xFF2E7D32),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductoCardPreview() {
    val productoDemo = Producto(
        brand = "Sony",
        name = "Audífonos Inalámbricos Noise Cancelling WH-1000XM5",
        price = 7499.00,
        rating = 4.8f,
        reviewCount = 230,
        isFreeShipping = true,
        description = "Cancelación de ruido de alta fidelidad y sonido envolvente.",
        image =  R.drawable.liverpool, // Reemplaza por R.drawable.tu_imagen si tienes un recurso local
        isFavorite = true
    )

    ProductoCard(
        producto = productoDemo,
        onFavoriteClick = {},
        onProductoClick = {}
    )
}