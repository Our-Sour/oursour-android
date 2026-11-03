package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.view.FeedScreen
import com.example.myapplication.view.IngredientsScreen
import com.example.myapplication.view.LoginScreen
import com.example.myapplication.view.FavoritosScreen
import com.example.myapplication.view.VerRecetaScreen
import com.example.myapplication.view.CarritoScreen

@Composable
fun AppNavigation(

){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.CARRITO

    ){
        composable(Routes.LOGIN){
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME)
                }
            )
        }
        composable(Routes.HOME) {
            IngredientsScreen(
                onVerRecetasClick = {  navController.navigate(Routes.FEED)}
            )
        }
        composable(Routes.FEED) {
            FeedScreen(
                onVerRecetaClick = { titulo ->
                    navController.navigate("ver_receta/$titulo")
                }
            )
        }
        composable(Routes.FAVORITOS) {
            FavoritosScreen()
        }


        //  Recibimos el título en la pantalla de receta
        composable("ver_receta/{titulo}") { backStackEntry ->
            val tituloRecibido = backStackEntry.arguments?.getString("titulo") ?: "Pisco Sour Clásico"

            VerRecetaScreen(
                tituloReceta = tituloRecibido,
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable(Routes.CARRITO) {
            //CarritoScreen( onVolverClick = { navController.popBackStack() } )
            CarritoScreen()
        }
    }
}