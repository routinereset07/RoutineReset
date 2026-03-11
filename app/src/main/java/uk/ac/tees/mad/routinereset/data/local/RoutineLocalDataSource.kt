package uk.ac.tees.mad.routinereset.data.local

import kotlinx.coroutines.flow.Flow

class RoutineLocalDataSource(
    private val routineDao: RoutineDao
) {
    suspend fun observeAllTasks(): Flow<List<RoutineTaskEntity>> =
        routineDao.getAllRoutineTasks()

    suspend fun observeTasksByRoutine(routineId: Int): Flow<List<RoutineTaskEntity>> =
        routineDao.getRoutineTasks(routineId)

    suspend fun getTaskById(taskId: Int): RoutineTaskEntity? =
        routineDao.getTaskById(taskId)

    suspend fun insertTask(task: RoutineTaskEntity) =
        routineDao.insertRoutineTask(task)

    suspend fun deleteTask(taskId: Int) =
        routineDao.deleteTaskById(taskId)

    suspend fun updateTaskCompletion(taskId: Int, isCompleted: Boolean) =
        routineDao.updateTaskCompletion(taskId, isCompleted)

    suspend fun updateTask(taskId: Int, title: String, description: String) =
        routineDao.updateTask(taskId, title, description)
}
