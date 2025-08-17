package com.student.core.domain.repository

import com.google.firebase.auth.AuthCredential

interface AuthRepository {
    suspend fun firebaseSignInWithGoogle(googleCredentials: AuthCredential): Boolean
}