package uk.ac.tees.mad.routinereset.notification

import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

class DailyNotificationScheduler(
    private val context: Context,
)
    : NotificationScheduler {
    override fun scheduleDaily() {
        Log.d("Notification", "scheduleDaily")
        scheduleAt(8, 0, "MORNING_REMINDER")
        scheduleAt(16, 0, "EVENING_REMINDER")
    }

    override fun cancel() {
        Log.d("Notification", "cancel")
        WorkManager.getInstance(context)
            .cancelUniqueWork("MORNING_REMINDER")

        WorkManager.getInstance(context)
            .cancelUniqueWork("EVENING_REMINDER")
    }

    private fun scheduleAt(hour: Int, minute: Int, tag: String) {
        Log.d("Notification", "scheduleAt")
        val now = Calendar.getInstance()
        val scheduled = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(now)) add(Calendar.DAY_OF_YEAR, 1)
        }

        val delay = scheduled.timeInMillis - now.timeInMillis

        val workRequest =
            OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .addTag(tag)
                .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                tag,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )

    }
}