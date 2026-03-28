package uk.ac.tees.mad.routinereset.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routines WHERE routineId = :routineId")
    fun getRoutineTasks(routineId: Int): Flow<List<RoutineTaskEntity>>

    @Query("SELECT * FROM routines")
    fun  getAllRoutineTasks(): Flow<List<RoutineTaskEntity>>

    @Query("select * FROM routines WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): RoutineTaskEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRoutineTask(routineTaskEntity: RoutineTaskEntity)

    //for the first time when user log in and fetches then---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutineTasks(routineTaskEntities: List<RoutineTaskEntity>)

    @Query("DELETE FROM routines WHERE taskId = :taskId")
    suspend fun deleteTaskById(taskId: String)

    @Query("UPDATE routines SET isCompleted = :isCompleted WHERE taskId = :taskId")
    suspend fun updateTaskCompletion(taskId: String, isCompleted: Boolean)

    @Query("UPDATE routines SET title = :title, description = :description WHERE taskId = :taskId")
    suspend fun updateTask(taskId: String, title: String, description: String)

    @Query("DELETE FROM routines")
    suspend fun deleteAllTasks()

    @Query("UPDATE routines SET isCompleted = 0")
    suspend fun resetAllTasks()

}