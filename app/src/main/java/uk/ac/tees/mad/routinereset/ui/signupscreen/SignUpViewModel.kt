package uk.ac.tees.mad.routinereset.ui.signupscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState = _signUpUiState.asStateFlow()

    fun onFullNameChange(fullName : String){
        val isValid = isFormValid(
            _signUpUiState.value.fullName,
            _signUpUiState.value.email,
            _signUpUiState.value.password,
            _signUpUiState.value.confirmPassword
        )
        _signUpUiState.update {
            it.copy(fullName = fullName,
                isSignUpEnabled = isValid,
                error = null
            )
        }
    }

    fun onEmailChange(email : String){
        val isValid = isFormValid(
            _signUpUiState.value.fullName,
            _signUpUiState.value.email,
            _signUpUiState.value.password,
            _signUpUiState.value.confirmPassword
        )
        _signUpUiState.update {
            it.copy(email = email,
                isSignUpEnabled = isValid,
                error = null
            )
        }
    }

    fun onPasswordChange(password : String){
        val isValid = isFormValid(
            _signUpUiState.value.fullName,
            _signUpUiState.value.email,
            _signUpUiState.value.password,
            _signUpUiState.value.confirmPassword
        )
        _signUpUiState.update {
            it.copy(password = password)
        }
    }

    fun onConfirmPasswordChange(confirmPassword : String){
        val isValid = isFormValid(
            _signUpUiState.value.fullName,
            _signUpUiState.value.email,
            _signUpUiState.value.password,
            _signUpUiState.value.confirmPassword
        )

        _signUpUiState.update {
            it.copy(confirmPassword = confirmPassword,
                isSignUpEnabled = isValid,
                error = null
            )
        }
    }

    fun onToggle(){
        _signUpUiState.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }

    fun signUp(){

    }

    private fun isFormValid(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if(name.isBlank()) return false
        if (email.isBlank()) return false
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) return false
        if (password.length < 8) return false
        if (password != confirmPassword) return false

        return true
    }




}