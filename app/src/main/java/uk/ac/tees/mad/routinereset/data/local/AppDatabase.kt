package uk.ac.tees.mad.routinereset.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [RoutineTaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun getRoutineDao(): RoutineDao
}