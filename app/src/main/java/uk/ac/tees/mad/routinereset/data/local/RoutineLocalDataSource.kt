package uk.ac.tees.mad.routinereset.data.local

import kotlinx.coroutines.flow.Flow

class RoutineLocalDataSource(
    private val routineDao: RoutineDao
) {
     fun observeAllTasks(): Flow<List<RoutineTaskEntity>> =
        routineDao.getAllRoutineTasks()

     fun observeTasksByRoutine(routineId: Int): Flow<List<RoutineTaskEntity>> =
        routineDao.getRoutineTasks(routineId)

    suspend fun getTaskById(taskId: Int): RoutineTaskEntity? =
        routineDao.getTaskById(taskId)

    suspend fun insertTask(task: RoutineTaskEntity) =
        routineDao.insertRoutineTask(task)

    suspend fun deleteTask(taskId: String) =
        routineDao.deleteTaskById(taskId)

    suspend fun updateTaskCompletion(taskId: String, isCompleted: Boolean) =
        routineDao.updateTaskCompletion(taskId, isCompleted)

    suspend fun updateTask(taskId: String, title: String, description: String) =
        routineDao.updateTask(taskId, title, description)

    suspend fun insertAllTask(tasks: List<RoutineTaskEntity>) =
        routineDao.insertAllRoutineTasks(tasks)

    suspend fun deleteAllTasks() =
        routineDao.deleteAllTasks()

    suspend fun resetAllTasks() =
        routineDao.resetAllTasks()
}
