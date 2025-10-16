package com.example.baitaptuan3

// TextFieldDetailScreen.kt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Sửa lại dòng này để nhận title và onBack
@Composable
fun TextFieldDetailScreen(title: String, onBack: () -> Unit) {
    // Bọc toàn bộ nội dung trong DetailScreenScaffold
    DetailScreenScaffold(title = title, onBack = onBack) {
        var textValue by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Thông tin nhập") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Dữ liệu đang nhập: $textValue")
        }
    }
}