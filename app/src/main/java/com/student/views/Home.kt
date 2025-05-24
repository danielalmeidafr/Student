package com.student.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home() {

}

@Preview
@Composable
fun HomePreview() {
    Box(Modifier.fillMaxSize().background(Color.White)) {
        Home()
    }
}