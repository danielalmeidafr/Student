package com.student.core.data.repository

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.student.core.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun firebaseSignInWithGoogle(googleCredentials: AuthCredential): Boolean {
        return try {

            val authResult = auth.signInWithCredential(googleCredentials).await()

            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false

            if (isNewUser){
                addUserToFirestore()
            }
            true


        } catch (e: Exception) {
            Log.d("GOOGLE_LOGIN", "firebaseSignInWithGoogle: ${e.localizedMessage}")
            false
        }
    }

    private suspend fun addUserToFirestore(){
        auth.currentUser?.apply {
            val user = toUser()
            firestore.collection("users").document(uid).set(user).await()
        }
    }

    fun FirebaseUser.toUser() = mapOf(
        "uid" to uid,
        "name" to displayName,
        "email" to email,
        "createAt" to serverTimestamp()
    )
}

