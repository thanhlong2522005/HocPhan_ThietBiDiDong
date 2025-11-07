package com.example.baitapvenha2tuan4.data.api

import com.example.baitapvenha2tuan4.data.model.Task
import com.example.baitapvenha2tuan4.data.model.TaskResponse
import com.example.baitapvenha2tuan4.data.model.TaskDetailResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): TaskResponse

    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): TaskDetailResponse

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}
