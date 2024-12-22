package com.financeviz.FinanceVizBackend.application.services

import com.google.firebase.auth.FirebaseAuth
import org.springframework.stereotype.Service

@Service
class AuthService(private val firebaseAuth: FirebaseAuth) {

    fun verifyToken(token: String): AuthResult {
        return try {
            val decodedToken = firebaseAuth.verifyIdToken(token)
            val uid = decodedToken.uid
            AuthResult(uid, "Token verified successfully", true)
        } catch (e: Exception) {
            AuthResult(null, "Invalid token: ${e.message}", false)
        }
    }
}

data class AuthResult(
    val uid: String?,
    val message: String,
    val success: Boolean
)
