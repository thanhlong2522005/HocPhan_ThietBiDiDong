@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.baitapvenha2tuan4.ui.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState // <- Vẫn giữ import này
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitapvenha2tuan4.data.api.TaskRetrofitClient
import com.example.baitapvenha2tuan4.data.model.Task
import kotlinx.coroutines.launch

@Composable
fun TaskListScreen(navController: NavController) {
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // --- CẬP NHẬT LOGIC TẢI DỮ LIỆU ---

    // 1. 🔹 THAY ĐỔI: Lắng nghe ID của task đã bị xóa
    val deletedTaskId = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<Int>("deleted_task_id") // <-- Key mới
        ?.observeAsState()

    // 2. Hàm này CHỈ dùng để tải lần đầu
    val loadInitialData = {
        scope.launch {
            isLoading = true
            try {
                val response = TaskRetrofitClient.instance.getTasks()
                tasks = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadInitialData()
    }

    LaunchedEffect(deletedTaskId?.value) {
        val idToRemove = deletedTaskId?.value
        if (idToRemove != null && idToRemove > 0) {
            println("🧩 Received delete event id=$idToRemove")

            scope.launch {
                isLoading = true
                try {
                    kotlinx.coroutines.delay(300)

                    val response = TaskRetrofitClient.instance.getTasks()
                    println("🧩 Reloaded tasks count=${response.data.size}")
                    tasks = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            }

            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<Int>("deleted_task_id")
        }
    }


    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                tasks.isEmpty() -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("List Empty", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("No tasks available. Create new tasks to get started.", color = Color.Gray)
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(tasks) { task ->
                            TaskCard(task = task, onClick = {
                                navController.navigate("task_detail/${task.id}")
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, onClick: () -> Unit) {
    // ✅ Ép kiểu an toàn sang String
    val safeStatus = when (val s = task.status) {
        is String -> s.lowercase()
        is Number -> s.toString()
        else -> "unknown"
    }

    val bgColor = when (safeStatus) {
        "in progress" -> Color(0xFFFFCDD2)
        "pending" -> Color(0xFFC8E6C9)
        "done", "completed" -> Color(0xFFBBDEFB)
        else -> Color(0xFFF5F5F5)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(bgColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                if (safeStatus.contains("progress") || safeStatus.contains("done")) {
                    Text("✓", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title ?: "No Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = task.description ?: "No Description",
                    maxLines = 2,
                    color = Color.DarkGray,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                val dueDateString = when (val d = task.dueDate) {
                    is String -> d
                    is Number -> d.toString()
                    else -> ""
                }
                val dateText = if (dueDateString.length >= 10) {
                    dueDateString.substring(0, 10)
                } else {
                    dueDateString
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.status?.toString() ?: "Unknown",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Text(
                        text = dateText,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
