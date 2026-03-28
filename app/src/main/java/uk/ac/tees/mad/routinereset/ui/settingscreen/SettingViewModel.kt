package uk.ac.tees.mad.routinereset.ui.settingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.domain.repository.RoutineRepository

class SettingViewModel(
    private val firebaseAuth: FirebaseAuth = AppModule.firebaseAuth,
    private val routineRepository: RoutineRepository = AppModule.routineRepository
): ViewModel() {
    private val _settingUiState = MutableStateFlow(SettingUiState())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState

    fun onLogoutClick(onSuccess:()-> Unit){
        firebaseAuth
            .signOut()
        onSuccess()
    }

    fun onClearDataClick(onSuccess: () -> Unit){
        viewModelScope.launch {
            routineRepository
                .deleteAllTasks()
            onSuccess()
        }
    }

    fun onResetClick(onSuccess: () -> Unit){
        viewModelScope.launch {
            routineRepository
                .resetAllTasks()
            onSuccess()
        }
    }

    fun onNotificationClick(value : Boolean){
        _settingUiState.update {
            it.copy(
                isNotificationOn = value
            )
        }

    }

    fun onSetReminderClick(function: () -> Unit) {

    }

}