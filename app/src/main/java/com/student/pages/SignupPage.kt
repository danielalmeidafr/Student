package com.student.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignupPage(modifier: Modifier = Modifier, navController: NavController) {
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
        Text(text = "Página de Cadastro", fontSize = 32.sp, color = Color.White)

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email:", color = Color.White.copy(0.5f), fontSize = 12.sp)
            },
            modifier = Modifier
                .fillMaxWidth(0.9f), shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF181818),
                unfocusedBorderColor = Color.White.copy(0.1F),
                focusedContainerColor = Color(0xFF181818),
                focusedBorderColor = Color.White.copy(0.1f)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Senha:", color = Color.White.copy(0.5f), fontSize = 12.sp)
            },
            modifier = Modifier
                .fillMaxWidth(0.9f), shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF181818),
                unfocusedBorderColor = Color.White.copy(0.1F),
                focusedContainerColor = Color(0xFF181818),
                focusedBorderColor = Color.White.copy(0.1f)
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier
                .height(39.dp)
                .width(260.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB29146),
                contentColor = Color.White
            )
        ) {
            Text(text = "Cadastre-se", fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text(
                buildAnnotatedString {
                    append("Ja possui uma conta? ")

                    withStyle(style = SpanStyle(color = Color(0XFFB29146))) {
                        append("Faça login")
                    }
                },
                color = Color.White, fontSize = 13.sp
            )
        }
    }
}