package com.example.baitaptuan3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColumnLayoutScreen(title: String, onBack: () -> Unit) {
    DetailScreenScaffold(title = title, onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp)
                        .background(Color(0xFF8BC34A))
                )
            }
        }
    }
}