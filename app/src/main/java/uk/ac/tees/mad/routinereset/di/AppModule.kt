package uk.ac.tees.mad.routinereset.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.routinereset.data.repository.AuthRepositoryImpl

object AppModule {
    val authRepository by lazy {
        AuthRepositoryImpl(
            auth = FirebaseAuth.getInstance(),
            firebaseFirestore = FirebaseFirestore.getInstance()
        )
    }
}