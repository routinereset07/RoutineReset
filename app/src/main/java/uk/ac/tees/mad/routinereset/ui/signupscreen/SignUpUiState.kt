package uk.ac.tees.mad.routinereset.ui.signupscreen

data class SignUpUiState(
    val fullName : String = "",
    val email : String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val isPasswordVisible : Boolean = false,
    val isLoading : Boolean = false,
    val isSignUpEnabled : Boolean = false,
    val error : String? = null,
    val success : Boolean = false,
)