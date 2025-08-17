package com.student.ui.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.ui.presentation.login.components.GoogleSignInButton
import com.student.ui.presentation.login.state.LoginState


@Composable
fun LoginScreen(
    uiState: LoginState,
    onNavigateToHome: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onEvent: (LoginEvent) -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(
        uiState.isSignInSuccessful,
        uiState.errorMessage
    ) {
        if (uiState.isSignInSuccessful){
            onNavigateToHome()
        }

        uiState.errorMessage?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(text = "Página de login", fontSize = 32.sp, color = Color.White)

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = {
                Text(text = "Email:", color = Color.White.copy(0.5f), fontSize = 12.sp)
            },
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.White.copy(0.8f)
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f), shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF181818),
                unfocusedBorderColor = Color.White.copy(0.1F),
                focusedContainerColor = Color(0xFF181818),
                focusedBorderColor = Color.White.copy(0.1f),
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = "Senha:", color = Color.White.copy(0.5f), fontSize = 12.sp)
            },
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.White.copy(0.8f)
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f), shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF181818),
                unfocusedBorderColor = Color.White.copy(0.1F),
                focusedContainerColor = Color(0xFF181818),
                focusedBorderColor = Color.White.copy(0.1f),
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onNavigateToHome,
            modifier = Modifier
                .height(39.dp)
                .width(260.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB29146),
                contentColor = Color.White
            )
        ) {
            Text(text = "Entrar", fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ou entre com",
            fontSize = 11.sp,
            color = Color.White.copy(0.8f),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .border(
                    width = 0.2.dp,
                    color = Color.White.copy(0.5f),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(3.dp)
        ) {
            GoogleSignInButton(
                isLoading = uiState.isLoading,
                onGoogleSignInClick = { onEvent.invoke(LoginEvent.GoogleSignInClick) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onNavigateToSignup
        ) {
            Text(
                buildAnnotatedString {
                    append("Não tem uma conta? ")

                    withStyle(style = SpanStyle(color = Color(0XFFB29146))) {
                        append("Cadastre-se")
                    }
                },
                color = Color.White, fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
