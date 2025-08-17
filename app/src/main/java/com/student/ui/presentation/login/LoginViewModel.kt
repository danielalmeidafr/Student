package com.student.ui.presentation.login

import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.core.domain.usecase.LoginUseCase
import com.student.core.manager.GoogleAuthManager
import com.student.core.util.Response
import com.student.ui.presentation.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val googleAuthManager: GoogleAuthManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.GoogleSignInClick -> {
                performGoogleSignIn()
            }
        }
    }

    private fun performGoogleSignIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val googleAuthResult = googleAuthManager.getGoogleAuthCredential()
            googleAuthResult.fold(
                onSuccess = { authCredential ->
                    loginUseCase.invoke(LoginUseCase.Parameters(authCredential))
                        .collect { response ->
                            when (response) {
                                is Response.Loading -> {
                                    _uiState.update {
                                        it.copy(
                                            isLoading = true
                                        )
                                    }
                                }

                                is Response.Failure -> {
                                    _uiState.update {
                                        it.copy(
                                            isLoading = false,
                                            errorMessage = response.errorMessage
                                        )
                                    }
                                }

                                is Response.Success -> {
                                    _uiState.update {
                                        it.copy(
                                            isLoading = false,
                                            isSignInSuccessful = response.data == true
                                        )
                                    }
                                }
                            }
                        }
                },
                onFailure = { throwable ->
                    if (throwable is GetCredentialCancellationException) {
                        _uiState.update { it.copy(isLoading = false) }
                    } else {
                        _uiState.update { it.copy(
                            isLoading = false,
                            errorMessage = throwable.message.toString()
                        ) }
                    }
                }
            )
        }
    }
}