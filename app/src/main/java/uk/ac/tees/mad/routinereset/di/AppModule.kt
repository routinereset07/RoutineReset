package uk.ac.tees.mad.routinereset.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.routinereset.data.local.AppDatabase
import uk.ac.tees.mad.routinereset.data.repository.AuthRepositoryImpl

object AppModule {
    val authRepository by lazy {
        AuthRepositoryImpl(
            auth = FirebaseAuth.getInstance(),
            firebaseFirestore = FirebaseFirestore.getInstance()
        )
    }

    lateinit var database: AppDatabase
        private set

    fun init(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "routine_reset_db"
        ).build()
    }
}