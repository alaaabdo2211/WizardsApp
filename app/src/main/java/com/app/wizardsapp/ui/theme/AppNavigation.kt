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
            Screens.WizardDetails.name + "/{itemId}" + "/{itemName}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType }, navArgument("itemName"){type = NavType.StringType})
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            val itemName = backStackEntry.arguments?.getString("itemName")

            WizardDetails(navController = navController, itemId.toString() , itemName.toString())
        }

        composable(  Screens.Elixirs.name + "/{elixirsId}" + "/{elixirsName}",
            arguments = listOf(navArgument("elixirsId") { type = NavType.StringType }, navArgument("elixirsName"){type = NavType.StringType})
        ) {backStackEntry ->
            val elixirsId = backStackEntry.arguments?.getString("elixirsId")
            val elixirsName = backStackEntry.arguments?.getString("elixirsName")

            ElixirsScreen(navController = navController, elixirsId.toString() , elixirsName.toString())
        }


    }
}

