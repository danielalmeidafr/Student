package com.student.ui.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var names = remember { mutableStateListOf<String>() }
    var presents = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
            .padding(WindowInsets.safeDrawing.asPaddingValues()),

    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            "Etec Albert Einstein",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0XFFB29146),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                value = name,
                onValueChange = { name = it },
                placeholder = {
                    Text(text = "Digite um nome:", color = Color.White.copy(0.5f), fontSize = 12.sp)
                },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = Color.White.copy(0.8f)
                ),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF181818),
                    unfocusedBorderColor = Color.White.copy(0.1F),
                    focusedContainerColor = Color(0xFF181818),
                    focusedBorderColor = Color.White.copy(0.1f),
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        if (!names.contains(name)) {
                            names += name
                        } else {
                            Toast.makeText(
                                context,
                                "Aluno já faz parte da lista.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Digite um nome antes de adicionar!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .height(56.dp)
                    .padding(top = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFFB29146),
                    contentColor = Color.White
                ),
            ) {
                Text("Adicionar", fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            "Lista de alunos:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(0.8f)
        )
        names.forEach { namesItem ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(
                        color = Color(0xFF181818),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    "○ $namesItem",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(0.8f),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        if (!presents.contains(name)) {
                            presents += name
                        } else {
                            Toast.makeText(
                                context,
                                "Aluno já listado como presente.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier.width(18.dp),
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.Green.copy(0.5f)
                    )
                }

                IconButton(
                    onClick = { names.remove(namesItem) }
                ) {
                    Icon(
                        modifier = Modifier.width(18.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Red.copy(0.5f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            "Lista de presentes:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(0.8f)
        )
        presents.forEach { presentsItem ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(
                        color = Color(0xFF181818),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    "○ $presentsItem",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(0.8f),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { presents.remove(presentsItem) }
                ) {
                    Icon(
                        modifier = Modifier.width(18.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Red.copy(0.5f)
                    )
                }
            }
        }
    }
}