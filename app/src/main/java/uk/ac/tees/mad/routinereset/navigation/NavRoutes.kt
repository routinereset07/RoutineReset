package uk.ac.tees.mad.routinereset.navigation

sealed class NavRoutes (val route: String) {
    object SignUp : NavRoutes("SignUp")
    object Login : NavRoutes("login")
    object Home : NavRoutes("home")
    object EditRoutine : NavRoutes("editRoutine")

}