package uk.ac.tees.mad.routinereset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.routinereset.navigation.AppNavHost
import uk.ac.tees.mad.routinereset.navigation.NavRoutes
import uk.ac.tees.mad.routinereset.ui.splashscreen.SplashUiState
import uk.ac.tees.mad.routinereset.ui.theme.RoutineResetTheme
import uk.ac.tees.mad.routinereset.ui.splashscreen.SplashViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashViewModel: SplashViewModel by viewModels()

        splashScreen.setKeepOnScreenCondition {
            splashViewModel.splashUiState.value is SplashUiState.Loading
        }
        setContent {
            val navController = rememberNavController()
            val startDestination = when (splashViewModel
                .splashUiState
                .collectAsState()
                .value) {
                SplashUiState.NavigateToLogin -> NavRoutes.Login.route
                SplashUiState.NavigateToHome -> NavRoutes.Home.route
                else -> null
            }
            if (startDestination != null) {
                RoutineResetTheme {
                    AppNavHost(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
