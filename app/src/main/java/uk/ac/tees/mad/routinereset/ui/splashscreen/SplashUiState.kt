package uk.ac.tees.mad.routinereset.ui.splashscreen

sealed class SplashUiState{
    object Loading : SplashUiState()
    object NavigateToLogin : SplashUiState()
    object NavigateToHome : SplashUiState()
    data class Error(val message: String) : SplashUiState()
}

// these four states will tell where to navigate
