package com.student

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.student.ui.presentation.home.HomeDestination
import com.student.ui.presentation.home.homeScreen
import com.student.ui.presentation.login.LoginDestination
import com.student.ui.presentation.login.loginScreen
import com.student.ui.presentation.signup.SignupDestination
import com.student.ui.presentation.signup.signupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = LoginDestination,
                modifier = Modifier.fillMaxSize()
            ) {
                loginScreen(
                    onNavigateToHome = { navController.navigate(HomeDestination) },
                    onNavigateToSignup = { navController.navigate(SignupDestination) }
                )

                signupScreen(
                    onNavigateToHome = { navController.navigate(HomeDestination) },
                    onNavigateToLogin = { navController.navigate(LoginDestination) }
                )

                homeScreen()
            }
        }
    }
}
