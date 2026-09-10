package br.edu.ifsp.scl.sc3038432.navigationintent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3038432.navigationintent.navigation.MainNavHost
import br.edu.ifsp.scl.sc3038432.navigationintent.navigation.Screen
import br.edu.ifsp.scl.sc3038432.navigationintent.ui.composable.component.MainTopAppBar
import br.edu.ifsp.scl.sc3038432.navigationintent.ui.theme.NavigationIntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navHostController = rememberNavController()

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()

            val showActions = navBackStackEntry?.destination?.route == Screen.IntentScreen.route

            NavigationIntentTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MainTopAppBar(
                            onNavigate = {destination -> navHostController.navigate(destination)},
                            showActions = showActions)
                    }
                ) { innerPadding ->
                    MainNavHost(
                        navHostController = navHostController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}