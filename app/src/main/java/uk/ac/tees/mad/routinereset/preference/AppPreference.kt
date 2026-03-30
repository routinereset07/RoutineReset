package uk.ac.tees.mad.routinereset.preference

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AppPreference {

    private const val PREF_NAME = "app_prefs"
    private const val KEY_FIRST_LAUNCH = "first_launch"
    private const val KEY_NOTIFICATION = "notification_enabled"

    private lateinit var prefs: SharedPreferences

    // ---- Internal mutable flows ----
    private val _firstLaunchFlow = MutableStateFlow(true)
    private val _notificationFlow = MutableStateFlow(true)


    // ---- Public immutable flows ----
    val firstLaunchFlow: StateFlow<Boolean> = _firstLaunchFlow
    val notificationFlow: StateFlow<Boolean> = _notificationFlow


    fun init(context: Context) {
        prefs = context.applicationContext
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        _firstLaunchFlow.value =
            prefs.getBoolean(KEY_FIRST_LAUNCH, true)

        _notificationFlow.value =
            prefs.getBoolean(KEY_NOTIFICATION, true)
    }


//for first time launch--->>
    fun setFirstLaunchDone() {
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
        _firstLaunchFlow.value = false
    }

 //for setting notification--->>
    fun setNotificationEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_NOTIFICATION, enabled).apply()
        _notificationFlow.value = enabled
    }


}