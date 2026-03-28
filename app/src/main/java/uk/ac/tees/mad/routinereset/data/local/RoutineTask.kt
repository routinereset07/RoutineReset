package uk.ac.tees.mad.routinereset.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "routines")
data class RoutineTaskEntity(
    val routineId : Int = 1, // 1 for morning 2 for evening
    @PrimaryKey val taskId : String = "",
    val title : String = "",
    val description : String = "",
    val isCompleted : Boolean = false
): Parcelable
