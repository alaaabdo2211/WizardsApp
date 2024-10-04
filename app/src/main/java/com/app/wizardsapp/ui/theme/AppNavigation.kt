package com.app.wizardsapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Wizards.name) {

        composable(Screens.Wizards.name) {
            WizardsScreen(navController = navController)
        }

        composable(
            Screens.WizardDetails.name + "{/itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            WizardDetails(navController = navController, itemId.toString())
        }

        composable(Screens.Elixirs.name) {
            WizardsScreen(navController = navController)
        }


    }
}

