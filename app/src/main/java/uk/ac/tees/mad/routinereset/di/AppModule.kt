package uk.ac.tees.mad.routinereset.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.routinereset.data.local.AppDatabase
import uk.ac.tees.mad.routinereset.data.local.RoutineLocalDataSource
import uk.ac.tees.mad.routinereset.data.remote.RoutineRemoteDataSource
import uk.ac.tees.mad.routinereset.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.routinereset.data.repository.RoutineRepositoryImpl

object AppModule {

    lateinit var database: AppDatabase
        private set
    val authRepository by lazy {
        AuthRepositoryImpl(
            auth = firebaseAuth,
            firebaseFirestore = firebaseFirestore
        )
    }

    val routineRepository by lazy {
        RoutineRepositoryImpl(
            local = RoutineLocalDataSource(
                routineDao = database.getRoutineDao()
            ),
            remote = RoutineRemoteDataSource(
                firestore = firebaseFirestore,
                auth = firebaseAuth
            )
        )
    }

    val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val firebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun init(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "routine_reset_db"
        ).build()
    }
}