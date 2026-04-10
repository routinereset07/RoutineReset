package uk.ac.tees.mad.routinereset.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.routinereset.data.local.RoutineLocalDataSource
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.data.remote.RoutineRemoteDataSource
import uk.ac.tees.mad.routinereset.domain.repository.RoutineRepository


class RoutineRepositoryImpl(
    private val local: RoutineLocalDataSource,
    private val remote: RoutineRemoteDataSource,

) : RoutineRepository {
    override suspend fun observeRoutineTasks(routineId: Int): Flow<List<RoutineTaskEntity>> {
        return local.observeTasksByRoutine(routineId)
    }

    override suspend fun observeAllRoutineTasks(): Flow<List<RoutineTaskEntity>> {
       return local.observeAllTasks()
    }

    override suspend fun getTaskById(
        taskId: String
    ): RoutineTaskEntity? {
        return local.getTaskById(taskId)
    }

    override suspend fun addTask(
        task: RoutineTaskEntity
    ) {
        // Firebase (source of truth)
        remote.addTask(task)
        //  Room (cache)
        Log.d("Repo","$task")
        local.insertTask(task)
    }

    override suspend fun deleteTask(
        taskId: String,
        routineId: Int
    ) {
        remote.deleteTask(taskId,routineId)
        local.deleteTask(taskId)
    }

    override suspend fun updateTaskCompletion(
        taskId: String,
        routineId: Int,
        isCompleted: Boolean
    ) {
        local.updateTaskCompletion(taskId, isCompleted)
    }

    override suspend fun updateTask(
        taskId: String,
        routineId: Int,
        title: String,
        description: String
    ) {
        //remote
        remote.updateTask(taskId,
            routineId,
            title,
            description)
        //local
        local.updateTask(taskId, title, description)
    }

    override suspend fun deleteAllTasks() {
        local.deleteAllTasks()
        remote.deleteAllTasks()
    }

    override suspend fun resetAllTasks() {
        local.resetAllTasks()
    }

    override suspend fun fetchAllTasks(){
        val tasks = remote.fetchAllTasks()
        local.insertAllTask(tasks)
    }
    override suspend fun resetTask(){
        local.resetAllTasks()
        remote.resetAllTask()
    }
}
