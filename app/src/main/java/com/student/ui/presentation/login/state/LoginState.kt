package com.student.ui.presentation.login.state

data class LoginState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val errorMessage: String? = null
)
