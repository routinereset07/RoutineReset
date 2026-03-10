package uk.ac.tees.mad.routinereset.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "routines")
data class RoutineTaskEntity(
    val routineId : Int, // 1 for morning 2 for evening
    @PrimaryKey val taskId : Int=0,
    val title : String,
    val description : String,
    val isCompleted : Boolean
)
