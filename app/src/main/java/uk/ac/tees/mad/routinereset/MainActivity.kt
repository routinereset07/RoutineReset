package uk.ac.tees.mad.routinereset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.routinereset.navigation.AppNavHost
import uk.ac.tees.mad.routinereset.navigation.NavRoutes
import uk.ac.tees.mad.routinereset.ui.theme.RoutineResetTheme

class MainActivity : ComponentActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     // enableEdgeToEdge()
        setContent {
            val isAuthenticated = firebaseAuth.currentUser != null
            val navController = rememberNavController()
            RoutineResetTheme {
                    AppNavHost(
                        navController = navController,
                        startDestination = if(isAuthenticated) NavRoutes.Home.route
                        else NavRoutes.Login.route
                    )
            }
        }
    }
}
