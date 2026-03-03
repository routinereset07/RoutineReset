package uk.ac.tees.mad.routinereset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.routinereset.navigation.AppNavHost
import uk.ac.tees.mad.routinereset.navigation.NavRoutes
import uk.ac.tees.mad.routinereset.ui.theme.RoutineResetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            RoutineResetTheme {
                    AppNavHost(
                        navController = navController,
                        startDestination = NavRoutes.Login.route
                    )
            }
        }
    }
}
