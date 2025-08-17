package com.student.core.domain.usecase

import com.google.firebase.auth.AuthCredential
import com.student.core.domain.repository.AuthRepository
import com.student.core.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LoginUseCase {
    suspend operator fun invoke(parameters: Parameters): Flow<Response<Boolean>>
    data class Parameters(val googleCredentials: AuthCredential)
}

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): LoginUseCase{
    override suspend fun invoke(parameters: LoginUseCase.Parameters): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading)
            val result = authRepository.firebaseSignInWithGoogle(parameters.googleCredentials)
            emit(Response.Success(result))
        }.catch {
            emit(Response.Failure(it.message.toString()))
        }
    }
}