package com.example.bnb

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT


interface ApiService {
    @GET("/flip-flap")
    fun flipFlap(): Call<ApiResponse>

    @GET("/rotate")
    fun rotate(): Call<ApiResponse>

    @GET("/get-timings")
    fun getTimings(): Call<TimingsResponse>

    @PUT("/update-time")
    fun updateTime(
        @Body request: UpdateTimeRequest
    ): Call<ApiResponse>
}

data class ApiResponse(
    val message: String? = null,
    val error: String? = null
)

data class TimingsResponse(
    val morning_time: String? = null,
    val evening_time: String? = null ,
    val night_time: String? = null,
    val error: String? = null
)

data class UpdateTimeRequest(
    val columnName: String,
    val timeString: String
)