package uk.ac.tees.mad.routinereset.ui.signupscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.domain.model.AuthResult
import uk.ac.tees.mad.routinereset.preference.AppPreference

class SignUpViewModel() : ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState = _signUpUiState.asStateFlow()

    private val authRepository = AppModule.authRepository

    fun onFullNameChange(fullName : String){
        val isValid = isFormValid(
          //  _signUpUiState.value.fullName,
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
           // _signUpUiState.value.fullName,
            email,
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
          //  _signUpUiState.value.fullName,
            _signUpUiState.value.email,
            password,
            _signUpUiState.value.confirmPassword
        )
        _signUpUiState.update {
            it.copy(password = password,
                isSignUpEnabled = isValid,
                error = null
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword : String){
        val isValid = isFormValid(
          //  _signUpUiState.value.fullName,
            _signUpUiState.value.email,
            _signUpUiState.value.password,
            confirmPassword
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

    fun signUp() {
        val state = _signUpUiState.value
        _signUpUiState.update {
            it.copy(isLoading = true, error = null)
        }
        viewModelScope.launch {
            val result = authRepository.signup(
                state.email,
                state.password
            )
            Log.d("SignUp","$result")
            when(result){
                is AuthResult.Error -> {
                    _signUpUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is AuthResult.Success -> {
                    // make first launch false else will duplicate the data
                    AppPreference.setFirstLaunchDone()
                    _signUpUiState.update {
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
        _signUpUiState.update {
            it.copy(success = false)
        }
    }

    private fun isFormValid(
       // name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
       // if(name.isBlank()) return false
        if (email.isBlank()) return false
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) return false
        if (password.length < 8) return false
        if (password != confirmPassword) return false
        return true
    }
}