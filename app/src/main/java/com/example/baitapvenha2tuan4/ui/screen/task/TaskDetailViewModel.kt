package com.example.baitapvenha2tuan4.ui.screen.task

import androidx.lifecycle.ViewModel
import com.example.baitapvenha2tuan4.data.api.TaskRetrofitClient
import com.example.baitapvenha2tuan4.data.model.Task

class TaskDetailViewModel : ViewModel() {
    private val api = TaskRetrofitClient.instance

    suspend fun getTaskDetail(taskId: Int): Task? {
        return try {
            val response = api.getTaskDetail(taskId)
            if (response.isSuccess) {
                response.data
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun deleteTask(taskId: Int): Boolean {
        return try {
            val response = api.deleteTask(taskId)
            println("🧩 DELETE Task $taskId → ${response.code()} / ${response.isSuccessful}")
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}