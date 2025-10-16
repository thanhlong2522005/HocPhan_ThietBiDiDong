package com.example.baitaptuan3

// RowLayoutScreen.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baitaptuan3.ui.theme.RowLayoutBoxColor // Import màu mới

@Composable
fun RowLayoutScreen(title: String, onBack: () -> Unit) {
    DetailScreenScaffold(title = title, onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            repeat(4) { // Trong hình có 4 hàng
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) { // Trong hình có 3 cột
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f) // Giúp ô vuông vắn hơn
                                .background(RowLayoutBoxColor)
                        )
                    }
                }
            }
        }
    }
}