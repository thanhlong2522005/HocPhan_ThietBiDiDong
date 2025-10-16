package com.example.baitaptuan3

// TextDetailScreen.kt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.baitaptuan3.ui.theme.BrownTextColor

// Sửa lại function signature
@Composable
fun TextDetailScreen(title: String, onBack: () -> Unit) {
    DetailScreenScaffold(title = title, onBack = onBack) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append("The ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick")
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 48.sp, color = BrownTextColor)) {
                        append("Brown")
                    }
                    append(" fox ")
                    withStyle(style = SpanStyle(letterSpacing = 0.2.em)) {
                        append("jumps")
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append("over")
                    }
                    append(" ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the")
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontSize = 24.sp, fontStyle = FontStyle.Italic)) {
                        append("lazy")
                    }
                    append(" dog.")
                },
                fontSize = 32.sp,
                lineHeight = 56.sp
            )
        }
    }
}