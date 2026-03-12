package uk.ac.tees.mad.routinereset.ui.settingscreen

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.ac.tees.mad.routinereset.di.AppModule

class SettingViewModel(
    private val firebaseAuth: FirebaseAuth = AppModule.firebaseAuth
): ViewModel() {

    private val _settingUiState = MutableStateFlow(SettingUiState())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState

    fun onLogoutClick(onSuccess:()-> Unit){
        firebaseAuth
            .signOut()
        onSuccess()
    }

    fun onClearDataClick(){

    }

    fun onResetClick(){

    }

    fun onNotificationClick(){

    }

    fun onSetReminderClick(){

    }

}