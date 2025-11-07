package com.example.baitapvenha2tuan4.ui.screen.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.baitapvenha2tuan4.data.model.Task
import com.example.baitapvenha2tuan4.data.model.Subtask
import com.example.baitapvenha2tuan4.data.model.Attachment
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.AttachFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    navController: NavController,
    viewModel: TaskDetailViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    var task by remember { mutableStateOf<Task?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(taskId) {
        scope.launch {
            try {
                val result = viewModel.getTaskDetail(taskId)

                if (result == null || result.title.isNullOrEmpty()) {
                    task = null
                } else {
                    task = result
                }

            } catch (e: Exception) {
                e.printStackTrace()
                task = null
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (task != null) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    val isSuccess = viewModel.deleteTask(taskId)

                                    if (isSuccess) {
                                        navController.previousBackStackEntry
                                            ?.savedStateHandle
                                            ?.set("deleted_task_id", taskId)
                                        navController.popBackStack()
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                isLoading -> {
                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                task == null -> {
                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Không có dữ liệu.", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                else -> {
                    TaskDetailContent(task = task!!)
                }
            }
        }
    }
}

@Composable
fun TaskDetailContent(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Tiêu đề
        Text(
            text = task.title ?: "No Title", // <-- SỬA Ở ĐÂY
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mô tả
        Text(
            text = task.description ?: "No Description", // <-- SỬA Ở ĐÂY
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Thông tin chung
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Category: ${task.category ?: "Unknown"}")
                Text("Status: ${task.status ?: "Unknown"}") // <-- SỬA Ở ĐÂY
                Text("Priority: ${task.priority ?: "Unknown"}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Subtasks ---
        Text("Subtasks", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        task.subtasks?.forEach { subtask: Subtask ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = subtask.isCompleted, onCheckedChange = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(subtask.title ?: "Untitled Subtask") // <-- SỬA Ở ĐÂY
            }
        } ?: Text("No subtasks available")

        Spacer(modifier = Modifier.height(16.dp))

        // --- Attachments ---
        Text("Attachments", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        task.attachments?.forEach { attachment: Attachment ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AttachFile, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(attachment.fileName ?: "Untitled Attachment") // <-- SỬA Ở ĐÂY
            }
        } ?: Text("No attachments available")
    }
}
