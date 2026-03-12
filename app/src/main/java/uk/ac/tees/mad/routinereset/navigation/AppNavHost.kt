package uk.ac.tees.mad.routinereset.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.routinereset.ui.EditRoutineScreen.EditRoutineScreen
import uk.ac.tees.mad.routinereset.ui.homescreen.HomeScreen
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
                    navController.navigate(NavRoutes.Home.route){
                        popUpTo(NavRoutes.Login.route){
                            inclusive = true
                        }
                    }
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
                    navController.navigate(NavRoutes.Home.route){
                        popUpTo(NavRoutes.SignUp.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = NavRoutes.Home.route
        ){
            HomeScreen(
                onEditRoutineClick = {
                    navController.navigate(NavRoutes.EditRoutine.route)
                },
                onSettingClick = {
                    navController.navigate(NavRoutes.Setting.route)
                }
            )
        }

        composable(NavRoutes.Setting.route){

        }


        composable(
            route = NavRoutes.EditRoutine.route
        ){
            EditRoutineScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}