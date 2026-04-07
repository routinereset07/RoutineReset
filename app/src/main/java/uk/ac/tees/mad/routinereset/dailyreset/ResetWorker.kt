package uk.ac.tees.mad.routinereset.dailyreset

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import uk.ac.tees.mad.routinereset.di.AppModule

class ResetTaskWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            AppModule.routineRepository.resetAllTasks()
            ResetScheduler.schedule(applicationContext)

            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }
    }
}
