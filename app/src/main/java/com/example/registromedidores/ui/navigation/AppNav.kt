package com.example.registromedidores.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registromedidores.ui.screens.AddReadingScreen
import com.example.registromedidores.ui.screens.ListScreen
import com.example.registromedidores.ui.viewmodel.ReadingViewModel

object Routes {
    const val LIST = "list"
    const val ADD = "add"
}

@Composable
fun AppNav(viewModel: ReadingViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST
    ) {
        composable(Routes.LIST) {
            ListScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(Routes.ADD) }
            )
        }

        composable(Routes.ADD) {
            AddReadingScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
