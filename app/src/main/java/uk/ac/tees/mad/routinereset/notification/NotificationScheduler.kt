package uk.ac.tees.mad.routinereset.notification

interface NotificationScheduler {
    fun scheduleDaily()
    fun cancel()
}