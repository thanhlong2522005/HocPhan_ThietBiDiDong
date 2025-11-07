package com.example.baitapvenha2tuan4.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Task>
)

data class Task(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("desImageURL")
    val desImageURL: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("status")
    val status: Any?,
    @SerializedName("priority")
    val priority: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("dueDate")
    val dueDate: Any?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("subtasks")
    val subtasks: List<Subtask>?,
    @SerializedName("attachments")
    val attachments: List<Attachment>?,
    @SerializedName("reminders")
    val reminders: List<Reminder>?
    // ...
) : Serializable

data class Subtask(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("isCompleted")
    val isCompleted: Boolean
) : Serializable

data class Attachment(
    @SerializedName("id")
    val id: Int,
    @SerializedName("fileName")
    val fileName: String?,
    @SerializedName("fileUrl")
    val fileUrl: String
) : Serializable

data class Reminder(
    @SerializedName("id")
    val id: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("type")
    val type: String
)

data class TaskDetailResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Task
)
