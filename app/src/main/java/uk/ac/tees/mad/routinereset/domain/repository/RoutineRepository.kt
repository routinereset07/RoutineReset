package uk.ac.tees.mad.routinereset.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity

interface RoutineRepository {

   suspend fun observeRoutineTasks(routineId: Int): Flow<List<RoutineTaskEntity>>
   suspend fun observeAllRoutineTasks(): Flow<List<RoutineTaskEntity>>
    suspend fun getTaskById(taskId: String): RoutineTaskEntity?

    suspend fun addTask(task: RoutineTaskEntity)

    suspend fun deleteTask(taskId: String, routineId: Int)

    suspend fun updateTaskCompletion(taskId: String,
                                     routineId: Int,
                                     isCompleted: Boolean)

    suspend fun updateTask(
        taskId: String,
        routineId: Int,
        title: String,
        description: String
    )
    suspend fun  deleteAllTasks()

    suspend fun resetAllTasks()
    suspend fun fetchAllTasks()
    suspend fun resetTask()
}
