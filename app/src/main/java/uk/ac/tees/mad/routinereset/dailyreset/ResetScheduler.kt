package uk.ac.tees.mad.routinereset.dailyreset

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object ResetScheduler {

    fun schedule(context: Context) {
        val now = System.currentTimeMillis()

        val calendar = Calendar.getInstance().apply {
            timeInMillis = now
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= now) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val delay = calendar.timeInMillis - now

        val request = OneTimeWorkRequestBuilder<ResetTaskWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .addTag("daily_reset")
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "daily_reset_work",
                ExistingWorkPolicy.REPLACE,
                request
            )
    }
}
