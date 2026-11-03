package com.example.myapplication.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.navigation.Routes
import com.example.myapplication.ui.theme.OurPeach40

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = listOf(
        BottomNavItem("Inicio", Icons.Default.Home, Routes.FEED),
        BottomNavItem("Ingredientes", Icons.AutoMirrored.Filled.List, Routes.INGREDIENTES), // 👈 Apunta a INGREDIENTES
        BottomNavItem("Favoritos", Icons.Default.Favorite, Routes.FAVORITOS),
        BottomNavItem("Carro", Icons.Default.ShoppingCart, Routes.CARRITO),
        BottomNavItem("Perfil", Icons.Default.Person, Routes.PERFIL)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                navItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label, tint = if (isSelected) OurPeach40 else Color.Gray) },
                        label = { Text(item.label, fontSize = 10.sp, color = if (isSelected) OurPeach40 else Color.Gray) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                // 👇 ESTO ARREGLA EL BUG DE QUEDARSE PEGADO AL VOLVER DE LA RECETA
                                popUpTo(navController.graph.findStartDestination().id) { saveState = false }
                                launchSingleTop = true
                                restoreState = false
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = OurPeach40,
                            indicatorColor = Color(0xFFFDEADE)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)