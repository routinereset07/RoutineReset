package uk.ac.tees.mad.routinereset.ui.loginscreen


data class LoginUiState(
    val email:String = "",
    val password:String = "",
    val isPasswordVisible:Boolean = false,
    val isLoginEnabled : Boolean = false,
    val isLoading :Boolean = false,
    val error:String? = null,
    val success: Boolean = false
)

