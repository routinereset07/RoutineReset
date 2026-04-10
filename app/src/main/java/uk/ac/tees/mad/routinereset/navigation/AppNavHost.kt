package uk.ac.tees.mad.routinereset.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uk.ac.tees.mad.routinereset.ui.RoutineListScreen.EditRoutineScreen
import uk.ac.tees.mad.routinereset.ui.editscreen.EditScreen
import uk.ac.tees.mad.routinereset.ui.homescreen.HomeScreen
import uk.ac.tees.mad.routinereset.ui.loginscreen.LoginScreen
import uk.ac.tees.mad.routinereset.ui.settingscreen.SettingScreen
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
            AnimatedScreen {
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
        }
        composable(
            route = NavRoutes.SignUp.route
        ){
            AnimatedScreen {
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
        }
        composable(
            route = NavRoutes.Home.route
        ){
            AnimatedScreen {
                HomeScreen(
                    onEditRoutineClick = {
                        navController.navigate(NavRoutes.EditRoutine.route)
                    },
                    onSettingClick = {
                        navController.navigate(NavRoutes.Setting.route)
                    }
                )
            }
        }

        composable(NavRoutes.Setting.route){
            AnimatedScreen {
                SettingScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onLogoutClick = {
                        navController.navigate(NavRoutes.Login.route){
                            popUpTo(NavRoutes.Setting.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        composable(
            route = NavRoutes.EditRoutine.route
        ){
            AnimatedScreen {
                EditRoutineScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                            taskId, routineId ->
                        navController.navigate(
                            NavRoutes.EditTask.editRoute(taskId, routineId)
                        )
                    },
                    onAddNewTask = {routineId->
                        navController.navigate(NavRoutes.EditTask.addRoute(routineId))
                    }
                )
            }
        }

        composable(
            route = NavRoutes.EditTask.route,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("routineId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId = backStackEntry.arguments?.getString("taskId")
            val routineId = backStackEntry.arguments!!.getInt("routineId")

            AnimatedScreen {
                EditScreen(
                    id = taskId,
                    routineId = routineId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
@Composable
fun AnimatedScreen(
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(300)
        ) + fadeIn(),
        exit = slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(300)
        ) + fadeOut()
    ) {
        content()
    }
}

