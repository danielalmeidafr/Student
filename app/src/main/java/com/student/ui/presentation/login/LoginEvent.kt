package com.student.ui.presentation.login

sealed class LoginEvent{
    data object GoogleSignInClick: LoginEvent()
}
