package mx.edu.utez.liverpool_felipe.ui.theme.screens.liverpool

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utez.liverpool_felipe.R
import mx.edu.utez.liverpool_felipe.models.Comentario
import mx.edu.utez.liverpool_felipe.models.Producto
import mx.edu.utez.liverpool_felipe.ui.theme.components.ComentarioCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    producto: Producto,
    onBackClick: () -> Unit = {},
    onFavoriteClick: (Producto) -> Unit = {},
    onAddToCartClick: (Producto) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir"
                        )
                    }
                    IconButton(onClick = { onFavoriteClick(producto) }) {
                        Icon(
                            imageVector = if (producto.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (producto.isFavorite) Color(0xFFE10098) else Color.Gray
                        )
                    }
                }
            )
        },
        bottomBar = {

            Surface(
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = { onAddToCartClick(producto) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE10098)
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Agregar a mi bolsa",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F4F9)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (producto.image != null) {
                        Image(
                            painter = painterResource(id = producto.image),
                            contentDescription = producto.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp)
                        )
                    } else {
                        Text(
                            text = "Sin imagen disponible",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE10098))
                        )
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = producto.brand.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )


            Text(
                text = producto.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                val fullStars = producto.rating.toInt()
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Estrella",
                        tint = if (index < fullStars) Color(0xFFFFB300) else Color.LightGray,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${producto.rating} (${producto.reviewCount} opiniones)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            if (producto.isFreeShipping) {
                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "  Envío gratis a todo el país",
                            color = Color(0xFF2E7D32),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Text(
                text = "$${String.format("%.2f", producto.price)}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE10098)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                text = "Descripción del producto",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = producto.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))


            Text(
                text = "Opiniones de los clientes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (producto.reviews.isNotEmpty()) {
                producto.reviews.forEach { comentario ->
                    ComentarioCard(comentario = comentario)
                    Spacer(modifier = Modifier.height(6.dp))
                }
            } else {
                Text(
                    text = "Aún no hay opiniones para este producto.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleScreenPreview() {
    val productoEjemplo = Producto(
        brand = "Sony",
        name = "Audífonos Inalámbricos Noise Cancelling WH-1000XM5",
        price = 7499.00,
        rating = 4.8f,
        reviewCount = 230,
        isFreeShipping = true,
        description = "La tecnología Noise Cancelling con múltiples micrófonos mantiene fuera los sonidos de alta y media frecuencia. Disfruta de llamadas nítidas con manos libres gracias a la tecnología Voice Pickup.",
        image = R.drawable.liverpool,
        isFavorite = true,
        reviews = listOf(
            Comentario(
                title = "Excelente cancelación de ruido",
                comment = "Los audífonos superaron mis expectativas, la batería dura bastante.",
                rating = 5.0f,
                author = "Felipe Ruiz"
            ),
            Comentario(
                title = "Muy cómodos",
                comment = "Buen sonido y súper livianos.",
                rating = 4.5f,
                author = "Ana Gómez"
            )
        )
    )

    DetalleScreen(
        producto = productoEjemplo,
        onBackClick = {},
        onFavoriteClick = {},
        onAddToCartClick = {}
    )
}