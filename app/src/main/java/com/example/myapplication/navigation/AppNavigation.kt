package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.view.FeedScreen
import com.example.myapplication.view.IngredientsScreen
import com.example.myapplication.view.LoginScreen

@Composable
fun AppNavigation(

){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN

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
            FeedScreen()
        }
    }
}