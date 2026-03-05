package uk.ac.tees.mad.routinereset.domain.repository

import uk.ac.tees.mad.routinereset.domain.model.AuthResult

interface AuthRepository {

    suspend fun login(
        email : String,
        password: String
    ) : AuthResult

    suspend fun signup(
        email : String,
        password: String
    ) : AuthResult

    suspend fun signOut()
}