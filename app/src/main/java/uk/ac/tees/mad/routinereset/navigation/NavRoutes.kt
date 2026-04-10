package uk.ac.tees.mad.routinereset.navigation

sealed class NavRoutes (val route: String) {
    object SignUp : NavRoutes("SignUp")
    object Login : NavRoutes("login")
    object Home : NavRoutes("home")
    object EditRoutine : NavRoutes("editRoutine")

    object Setting : NavRoutes("setting")

    object EditTask : NavRoutes(
        "editTask?taskId={taskId}&routineId={routineId}"
    ) {

        fun addRoute(routineId: Int): String =
            "editTask?routineId=$routineId"
        fun editRoute(taskId: String, routineId: Int): String =
            "editTask?taskId=$taskId&routineId=$routineId"
    }

}