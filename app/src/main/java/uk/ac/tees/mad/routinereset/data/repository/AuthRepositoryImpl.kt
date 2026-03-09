package uk.ac.tees.mad.routinereset.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.routinereset.domain.model.AuthResult
import uk.ac.tees.mad.routinereset.domain.model.UserProfile
import uk.ac.tees.mad.routinereset.domain.repository.AuthRepository

class AuthRepositoryImpl (
    private val auth : FirebaseAuth,
    private val firebaseFirestore : FirebaseFirestore
) : AuthRepository{
    override suspend fun login(
        email: String,
        password: String
    ): AuthResult {
        return  try {
            val result = auth
                .signInWithEmailAndPassword(email,password)
                .await()

            val userId = result.user?.uid
                ?: return AuthResult.Error("Login failed")
            AuthResult.Success(userId)

        }catch(e : Exception){
            return AuthResult.Error(mapFirebaseError(e))
        }
    }

    override suspend fun signup(
        email: String,
        password: String
    ): AuthResult {

        return try{
            val result = auth
                .createUserWithEmailAndPassword(email,password)
                .await()

            val userId = result.user?.uid
                ?: return AuthResult.Error("Signup failed")

            // Save user profile in Firestore
            saveUserProfile(userId, email)

            AuthResult.Success(userId)

        }catch(e : Exception){
            AuthResult.Error(mapFirebaseError(e))
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    private suspend fun saveUserProfile(
        userId: String,
        email: String,
        //fullName: String
    ) {
        val user = UserProfile(
            uid = userId,
            email = email
        )
        firebaseFirestore
            .collection("users")
            .document(userId)
            .set(user)
            .await()
    }

    private fun mapFirebaseError(e: Exception): String {
        return when (e) {
            is FirebaseAuthInvalidUserException ->
                "No account found with this email"

            is FirebaseAuthInvalidCredentialsException ->
                "Incorrect email or password"

            is FirebaseAuthUserCollisionException ->
                "Account already exists"

            else ->
                "Something went wrong. Please try again."
        }
    }
}