package uk.ac.tees.mad.routinereset.ui.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.domain.model.AuthResult

class LoginViewModel : ViewModel(){
    private val authRepository = AppModule.authRepository
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun onEmailChange(email :String){
        val isLoginValid = isValid(email = email,
            password = _loginUiState.value.password)
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


    fun login() {
        val state = _loginUiState.value
        _loginUiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (
                val result = authRepository.login(
                    state.email,
                    state.password
                )
            ) {
                is AuthResult.Error -> {
                    _loginUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is AuthResult.Success -> {
                    _loginUiState.update {
                        it.copy(
                            isLoading = false,
                            success = true
                        )
                    }
                }
            }
        }
    }

    fun resetSuccess() {
        _loginUiState.update {
            it.copy(success = false)
        }
    }



    private fun isValid(email: String, password: String): Boolean {
        return email.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length >= 8
    }

}