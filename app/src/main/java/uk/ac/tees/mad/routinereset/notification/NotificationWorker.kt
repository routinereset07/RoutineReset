package uk.ac.tees.mad.routinereset.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotificationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        NotificationHelper.show(
            applicationContext,
            title = "Routine Reminder",
            message = "Time to complete your routine"
        )
        // Reschedule for next day
        NotificationModule.notificationScheduler.scheduleDaily()
        return Result.success()
    }
}

