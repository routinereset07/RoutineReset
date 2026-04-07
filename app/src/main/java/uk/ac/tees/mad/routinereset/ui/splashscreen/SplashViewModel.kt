package uk.ac.tees.mad.routinereset.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.notification.NotificationModule


class SplashViewModel(
    private val firebaseAuth: FirebaseAuth = AppModule.firebaseAuth,
) : ViewModel() {

    private val _splashUiState =
        MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()

    private val notificationScheduler =
        NotificationModule.notificationScheduler

    init {
        resolveSplash()
    }

    private fun resolveSplash() {
        viewModelScope.launch {

            if (!isUserLoggedIn()) {
                _splashUiState.value = SplashUiState.NavigateToLogin
                return@launch
            }

//            if (AppPreference.isNotificationEnabled()) {
//                Log.d("splash", "enabled")
//                notificationScheduler.scheduleDaily()
//            } else {
//                Log.d("Notification", "disabled")
//                notificationScheduler.cancel()
//            }
            _splashUiState.value = SplashUiState.NavigateToHome
        }
    }

    private fun isUserLoggedIn(): Boolean =
        firebaseAuth.currentUser != null

}

