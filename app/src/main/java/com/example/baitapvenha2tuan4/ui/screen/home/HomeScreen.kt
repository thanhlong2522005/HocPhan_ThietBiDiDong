package com.example.baitapvenha2tuan4.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitapvenha2tuan4.R
import com.example.baitapvenha2tuan4.ui.screen.detail.DetailScreen
import com.example.baitapvenha2tuan4.ui.screen.product.ProductScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.baitapvenha2tuan4.ui.screen.task.TaskListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            // 🔼 Header phía trên (to hơn, đẹp hơn)
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.uth_logo),
                            contentDescription = "UTH Logo",
                            modifier = Modifier
                                .size(55.dp)
                                .padding(end = 12.dp)
                        )
                        Column {
                            Text(
                                text = "SmartTasks",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "A simple and efficient to-do app",
                                fontSize = 15.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    // 🔔 Icon chuông + chấm vàng
                    Box(contentAlignment = Alignment.TopEnd) {
                        IconButton(onClick = { /* TODO: Notification click */ }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .offset(x = (-4).dp, y = 8.dp)
                                .clip(CircleShape)
                                .background(Color.Yellow)
                        )
                    }
                }
            }
        },

        bottomBar = {
            // 🧭 Thanh điều hướng dưới
            NavigationBar {
                val items = listOf(
                    Icons.Default.Home,
                    Icons.Default.AccountCircle,
                    Icons.Default.ShoppingCart,
                    Icons.Default.Description,
                    Icons.Default.Settings
                )
                val labels = listOf("Home", "Profile", "Product", "Notes", "Settings")

                items.forEachIndexed { index, icon ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = { Icon(icon, contentDescription = labels[index]) },
                        label = { Text(labels[index]) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedItem) {
                0 -> TaskListScreen(navController = navController)
                1 -> DetailScreen(navController = navController)
                2 -> ProductScreen(navController = navController)
                3 -> Text(
                    "Notes – Coming soon",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
                4 -> Text(
                    "Settings – Coming soon",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
