package uk.ac.tees.mad.routinereset.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routines WHERE routineId = :routineId")
    suspend fun getRoutineTasks(routineId: Int): List<RoutineTaskEntity>

    @Query("SELECT * FROM routines")
    suspend fun  getAllRoutineTasks(): List<RoutineTaskEntity>

    @Query("select * FROM routines WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): RoutineTaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutineTask(routineTaskEntity: List<RoutineTaskEntity>)

    @Query("DELETE FROM routines WHERE taskId = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query("UPDATE routines SET isCompleted = :isCompleted WHERE taskId = :taskId")
    suspend fun updateTaskCompletion(taskId: Int, isCompleted: Boolean)

    @Query("UPDATE routines SET title = :title, description = :description WHERE taskId = :taskId")
    suspend fun updateTask(taskId: Int, title: String, description: String)

}