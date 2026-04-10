package uk.ac.tees.mad.routinereset.preference

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.core.content.edit
import kotlinx.coroutines.flow.asStateFlow

object AppPreference {
    private const val PREF_NAME = "app_prefs"
    private const val KEY_FIRST_LAUNCH = "first_launch"
    private const val KEY_NOTIFICATION = "notification_enabled"


    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.applicationContext
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    fun setFirstLaunchDone() {
        prefs.edit { putBoolean(KEY_FIRST_LAUNCH, false) }
    }


    fun setNotificationEnabled(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_NOTIFICATION, enabled) }
    }

    fun isFirstLaunch(): Boolean {
        return prefs.getBoolean("first_launch", true)
    }

    fun isNotificationEnabled(): Boolean {
        return prefs.getBoolean(KEY_NOTIFICATION, true)
    }

}