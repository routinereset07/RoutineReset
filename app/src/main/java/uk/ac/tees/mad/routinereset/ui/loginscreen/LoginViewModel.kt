package uk.ac.tees.mad.routinereset.ui.loginscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel(){
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun onEmailChange(email :String){
        val isLoginValid = isValid(email = email, password = _loginUiState.value.password)
        _loginUiState.update {
            it.copy(
                email = email,
                isLoginEnabled = isLoginValid,
                error = null
            )
        }
    }

    fun onPasswordChange(password :String){
        val isLoginValid = isValid(password = password,
            email = _loginUiState.value.email)
        _loginUiState.update {
            it.copy(
                password = password,
                isLoginEnabled = isLoginValid,
                error = null
            )
        }
    }

    fun onPasswordToggle(){
        _loginUiState.update {
            it.copy(
               isPasswordVisible = !it.isPasswordVisible
            )
        }
    }


    fun login(){
        /* handle login here */

    }


    private fun isValid(email: String, password: String): Boolean {
        return email.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length >= 8
    }
}