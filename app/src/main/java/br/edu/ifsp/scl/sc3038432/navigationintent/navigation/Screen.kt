package br.edu.ifsp.scl.sc3038432.navigationintent.navigation

sealed class Screen(val route: String) {
    object IntentScreen: Screen("intent_screen")
    object ParameterScreen: Screen("parameter_screen")
}