package mx.edu.utez.liverpool_felipe.ui.theme.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.edu.utez.liverpool_felipe.ui.theme.screens.liverpool.DetalleScreen
import mx.edu.utez.liverpool_felipe.ui.theme.screens.liverpool.ProductoScreen
import mx.edu.utez.liverpool_felipe.ui.theme.screens.liverpool.ProductoViewModel
import mx.edu.utez.liverpool_felipe.ui.theme.screens.menu.MenuScreen

sealed class Route(val path: String) {
    object Menu : Route("menu")
    object Liverpool : Route("liverpool")
    object Detalle : Route("detalle")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ProductoViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Route.Menu.path,
        modifier = modifier
    ) {
        composable(Route.Menu.path) {
            MenuScreen(
                onNavigateToLiverpool = { navController.navigate(Route.Liverpool.path) }
            )
        }

        composable(Route.Liverpool.path) {
            ProductoScreen(
                onBackClick = { navController.popBackStack() },
                onProductoClick = { producto ->
                    viewModel.seleccionarProducto(producto)
                    navController.navigate(Route.Detalle.path)
                },
                viewModel = viewModel
            )
        }

        composable(Route.Detalle.path) {
            val producto = viewModel.productoSeleccionado
            if (producto != null) {
                DetalleScreen(
                    producto = producto,
                    onBackClick = { navController.popBackStack() },
                    onFavoriteClick = { prod -> viewModel.Favorite(prod) }
                )
            }
        }
    }
}