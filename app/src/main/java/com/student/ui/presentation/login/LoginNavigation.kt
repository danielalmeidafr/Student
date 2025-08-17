package com.student.ui.presentation.login

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object LoginDestination

fun NavGraphBuilder.loginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    composable<LoginDestination> {

        val viewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LoginScreen(
            uiState = uiState,
            onNavigateToHome = { onNavigateToHome() },
            onNavigateToSignup = { onNavigateToSignup() },
            onEvent = { viewModel.onEvent(it) }
        )
    }
}