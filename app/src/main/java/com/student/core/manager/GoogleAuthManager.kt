package com.student.core.manager

import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import android.content.Context
import androidx.credentials.CustomCredential
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.student.core.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Singleton

@Singleton
class GoogleAuthManager @Inject constructor(
    private val credentialManager: CredentialManager,
    @ApplicationContext private val context: Context
) {

    suspend fun getGoogleAuthCredential(requiredAuthorized: Boolean = false): Result<AuthCredential> {

        return withContext(Dispatchers.IO) {
            try {
                val hashNonce = UUID.randomUUID().toString()

                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(requiredAuthorized)
                    .setServerClientId(Constants.GOOGLE_CLIENT_ID)
                    .setNonce(hashNonce)
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption).build()

                val credential = credentialManager.getCredential(context, request)
                handleCredentialResponse(credential)

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun handleCredentialResponse(credential: GetCredentialResponse): Result<AuthCredential> {
        return when(val credential = credential.credential){
            is CustomCredential ->{
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                    try {

                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                        val idToken = googleIdTokenCredential.idToken
                        val result = GoogleAuthProvider.getCredential(idToken, null)

                        Result.success(result)
                    } catch (e: GetCredentialException){
                        Result.failure(e)
                    } catch (e: GoogleIdTokenParsingException){
                        Result.failure(e)
                    }
                } else {
                    Result.failure(Exception("Tipo de credencial invalida"))
                }
            } else -> {
                Result.failure(Exception("credencial invalida"))
            }
        }
    }
}