package uk.ac.tees.mad.routinereset.ui.signupscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import uk.ac.tees.mad.routinereset.ui.signupscreen.component.ConfirmPasswordField
import uk.ac.tees.mad.routinereset.ui.signupscreen.component.SignUpHeader
import uk.ac.tees.mad.routinereset.ui.signupscreen.component.SignUpTopBar


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    onNavBackClick: () -> Unit,
    onNavHomeClick:()-> Unit,
){
    val uiState by signUpViewModel.signUpUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onNavHomeClick()
            signUpViewModel.resetSuccess()
        }
    }

    SignUpComponent(onNavBackClick = onNavBackClick,
        fullName = uiState.fullName,
        email = uiState.email,
        password = uiState.password,
        confirmPassword = uiState.confirmPassword,
        onFullNameChange = signUpViewModel::onFullNameChange,
        onEmailChange = signUpViewModel::onEmailChange,
        onPasswordChange = signUpViewModel::onPasswordChange,
        onConfirmPasswordChange = signUpViewModel::onConfirmPasswordChange,
        isSignUpEnabled = uiState.isSignUpEnabled,
        isLoading = uiState.isLoading,
        error = uiState.error,
        success = uiState.success,
        isPasswordVisible = uiState.isPasswordVisible,
        onToggle = signUpViewModel::onToggle,
        onSignUpClick = signUpViewModel::signUp,
        onNavHomeClick = onNavHomeClick
    )
}


@Composable
fun SignUpComponent(
    onNavBackClick: () -> Unit,
    onSignUpClick: () -> Unit,
    success: Boolean,
    error: String?,
    isLoading: Boolean,
    isSignUpEnabled: Boolean,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onFullNameChange: (String) -> Unit,
    password: String,
    confirmPassword: String,
    email: String,
    fullName: String,
    isPasswordVisible: Boolean,
    onToggle: () -> Unit,
    onNavHomeClick: () -> Unit //this will be triggered when signup succeed
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SignUpTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .statusBarsPadding(),
            onNavBackClick = onNavBackClick
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        SignUpHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .imePadding()    ,
            verticalArrangement = Arrangement.Center,

        ) {

            Label(
                text = "Email Address",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            EmailField(
                email = email,
                onEmailChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Label(
                text = "password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            PasswordField(
                password = password,
                passwordVisible = isPasswordVisible,
                onPasswordChange = onPasswordChange,
                onToggleVisibility = onToggle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Label(
                text = "confirm password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            ConfirmPasswordField(
                password = confirmPassword,
                onPasswordChange = onConfirmPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )
            Button(
                enabled = isSignUpEnabled,
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Create Account"
                )
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text(
                text = "Already have account?log in",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onNavBackClick()
                },
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    SignUpComponent(
        onNavBackClick = {},
        onSignUpClick = {},
        success = false,
        error = null,
        isLoading = false,
        isSignUpEnabled = true,
        onConfirmPasswordChange = {},
        onPasswordChange = {},
        onEmailChange = {},
        onFullNameChange = {},
        password = "12345",
        confirmPassword = "arman123",
        email = "arman@gmail.com",
        fullName = "arman",
        isPasswordVisible = true,
        onToggle = {},
        onNavHomeClick = {}
    )
}