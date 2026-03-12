package uk.ac.tees.mad.routinereset.ui.settingscreen

data class SettingUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isNotificationOn : Boolean = false
)