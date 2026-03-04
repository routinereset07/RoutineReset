package uk.ac.tees.mad.routinereset.ui.loginscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.ui.components.EmailField
import uk.ac.tees.mad.routinereset.ui.components.Label
import uk.ac.tees.mad.routinereset.ui.components.PasswordField
import uk.ac.tees.mad.routinereset.ui.loginscreen.component.LoginHeader


@Composable
fun LoginScreen(
    navigateToSignup:()-> Unit,
    navigateToHome:()-> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    val uiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()

    LoginScreenComponent(
        email = uiState.email,
        password = uiState.password,
        passwordVisible = uiState.isPasswordVisible,
        onEmailChange = loginViewModel::onEmailChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        onPasswordToggle = loginViewModel::onPasswordToggle,
        onLoginClick = loginViewModel::login,
        isLoginEnabled = uiState.isLoginEnabled,
        isLoading = uiState.isLoading,
        error = uiState.error,
        success = uiState.success,
        navigateToSignup = navigateToSignup
    )
}


@Composable
private fun LoginScreenComponent(
    email: String,
    password: String,
    passwordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordToggle: () -> Unit,
    onLoginClick: () -> Unit,
    isLoginEnabled: Boolean,
    isLoading: Boolean,
    error: String?,
    success: Boolean,
    navigateToSignup: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
            .imePadding() ,
        verticalArrangement = Arrangement.Center
    ) {

        LoginHeader()

        Spacer(modifier = Modifier.height(40.dp))

        Label(text = "Email Address")
        EmailField(email, onEmailChange, Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Label(text = "Password")
        PasswordField(
            password = password,
            passwordVisible = passwordVisible,
            onPasswordChange = onPasswordChange,
            onToggleVisibility = onPasswordToggle,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            enabled = isLoginEnabled,
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "don't have an account? sign up",
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    navigateToSignup()
                },
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
        LoginScreenComponent(
            email = "",
            password = "",
            passwordVisible = false,
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordToggle = {},
            onLoginClick = {},
            isLoginEnabled = true,
            isLoading = false,
            error = null,
            success = false,
            navigateToSignup = {}
        )
    }

