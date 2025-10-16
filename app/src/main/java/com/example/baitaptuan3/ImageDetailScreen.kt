package com.example.baitaptuan3

// ImageDetailScreen.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.baitaptuan3.R

// Sửa lại dòng này để nhận title và onBack
@Composable
fun ImageDetailScreen(title: String, onBack: () -> Unit) {
    // Bọc toàn bộ nội dung trong DetailScreenScaffold
    DetailScreenScaffold(title = title, onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png")
            AsyncImage(
                model = "https://tse2.mm.bing.net/th/id/OIP.IBqQSEGbeqDmv6yXHAR-MgHaEK?pid=Api&P=0&h=180",
                contentDescription = "https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text("In app")
            Image(
                painter = painterResource(id = R.drawable.uth_hcmc),
                contentDescription = "Image from app",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}