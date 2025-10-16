package com.example.baitaptuan3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan3.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bạn cần thêm một ảnh logo vào thư mục res/drawable
        // và đặt tên là 'jetpack_compose_logo.png'
        Image(
            painter = painterResource(id = R.drawable.jetpack_compose_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Jetpack Compose",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate(Routes.COMPONENT_LIST) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("I'm ready")
        }
    }
}