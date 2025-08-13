package com.student

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.student.pages.HomePage
import com.student.pages.LoginPage
import com.student.pages.SignupPage

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginPage(modifier, navController)
        }

        composable("signup") {
            SignupPage(modifier, navController)
        }

        composable("home") {
            HomePage(modifier, navController)
        }
    })
}