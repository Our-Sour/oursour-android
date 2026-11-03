package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.view.*

@Composable
fun AppNavigation(
    navController: NavHostController, // 👈 Recibe esto
    modifier: Modifier = Modifier,     // 👈 Recibe esto
    onLogout: () -> Unit = {}          // 👈 Y recibe esto
) {
    NavHost(
        navController = navController,
        startDestination = Routes.FEED,
        modifier = modifier
    ) {
        composable(Routes.FEED) {
            FeedScreen(
                onNavigateToCarrito = { navController.navigate(Routes.CARRITO) },
                onVerRecetaClick = { titulo -> navController.navigate("ver_receta/$titulo") }
            )
        }

        composable(Routes.INGREDIENTES) {
            IngredientsScreen(onVerRecetasClick = { navController.navigate(Routes.FEED) })
        }

        composable(Routes.FAVORITOS) { FavoritosScreen() }
        composable(Routes.CARRITO) { CarritoScreen() }
        composable(Routes.NOTIFICACIONES) { NotificacionesScreen() }

        composable(Routes.VERRECETA) { backStackEntry ->
            val tituloRecibido = backStackEntry.arguments?.getString("titulo") ?: "Pisco Sour Clásico"
            VerRecetaScreen(
                tituloReceta = tituloRecibido,
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable(Routes.PERFIL) {
            PerfilScreen(
                onCerrarSesionClick = onLogout // 👈 Aquí conectamos el botón de cerrar sesión
            )
        }
    }
}