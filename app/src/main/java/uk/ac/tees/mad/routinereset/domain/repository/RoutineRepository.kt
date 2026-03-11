package uk.ac.tees.mad.routinereset.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity

interface RoutineRepository {

   suspend fun observeRoutineTasks(routineId: Int): Flow<List<RoutineTaskEntity>>
   suspend fun observeAllRoutineTasks(): Flow<List<RoutineTaskEntity>>
    suspend fun getTaskById(taskId: Int): RoutineTaskEntity?

    suspend fun addTask(task: RoutineTaskEntity)

    suspend fun deleteTask(taskId: Int, routineId: Int)

    suspend fun updateTaskCompletion(taskId: Int,
                                     routineId: Int,
                                     isCompleted: Boolean)

    suspend fun updateTask(
        taskId: Int,
        routineId: Int,
        title: String,
        description: String
    )
}
