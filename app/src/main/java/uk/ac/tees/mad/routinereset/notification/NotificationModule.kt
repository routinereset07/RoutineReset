package uk.ac.tees.mad.routinereset.notification

import android.content.Context

object NotificationModule {

    lateinit var notificationScheduler: NotificationScheduler
        private set

    fun init(context: Context) {
        val appContext = context.applicationContext
        notificationScheduler = DailyNotificationScheduler(appContext)
    }
}
