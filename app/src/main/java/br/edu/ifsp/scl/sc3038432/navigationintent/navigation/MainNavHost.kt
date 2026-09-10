package br.edu.ifsp.scl.sc3038432.navigationintent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.edu.ifsp.scl.sc3038432.navigationintent.MainViewModel
import br.edu.ifsp.scl.sc3038432.navigationintent.ui.composable.screen.IntentScreen
import br.edu.ifsp.scl.sc3038432.navigationintent.ui.composable.screen.ParameterScreen

@Composable
fun MainNavHost(navHostController: NavHostController, modifier: Modifier, mainViewModel: MainViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.IntentScreen.route
    ) {
        // Nós que fazem parte do grafo
        composable(route = Screen.IntentScreen.route) {
            IntentScreen(
                receivedParameter = mainViewModel.parameter,
                modifier = modifier)
        }

        composable(route = Screen.ParameterScreen.route) {
            ParameterScreen(
                receivedParameter = mainViewModel.parameter,
                modifier = modifier,
                onSaveAndQuit = { parameter ->
                    mainViewModel.updateParameter(parameter)
                    navHostController.popBackStack()
                }
            )
        }
    }
}