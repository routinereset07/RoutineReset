package uk.ac.tees.mad.routinereset.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.routinereset.ui.loginscreen.LoginScreen
import uk.ac.tees.mad.routinereset.ui.signupscreen.SignUpScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = NavRoutes.Login.route
        ){
            LoginScreen(
                navigateToSignup = {
                    navController.navigate(NavRoutes.SignUp.route)
                },
                navigateToHome = {
                    navController.navigate(NavRoutes.Home.route)
                }
            )
        }
        composable(
            route = NavRoutes.SignUp.route
        ){
            SignUpScreen(
                onNavBackClick = {
                    navController.popBackStack()
                },
                onNavHomeClick = {
                    navController.navigate(NavRoutes.Home.route)
                }
            )
        }
        composable(
            route = NavRoutes.Home.route
        ){
//for the home screen---->>>>
        }
    }
}